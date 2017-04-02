package com.melorriaga.kokemon.view.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.app
import com.melorriaga.kokemon.injection.module.MainModule
import com.melorriaga.kokemon.interactor.main.MainInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.main.MainPresenter
import com.melorriaga.kokemon.presenter.main.MainPresenterImpl
import com.melorriaga.kokemon.presenter.loader.PresenterFactory
import com.melorriaga.kokemon.view.base.BaseRetainFragment
import com.melorriaga.kokemon.view.common.PokemonRecyclerViewAdapter
import com.melorriaga.kokemon.view.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class MainFragment : BaseRetainFragment<MainPresenter, MainView>(), MainView {

    companion object {
        val TAG = "MainFragment"
    }

    @Inject
    lateinit var interactor: MainInteractor

    lateinit private var pokemonRecyclerViewAdapter: PokemonRecyclerViewAdapter

    override fun injectDependencies() {
        app.applicationComponent
                .plus(MainModule())
                .injectTo(this)
    }

    override fun presenterFactory(): PresenterFactory<MainPresenter> {
        return object : PresenterFactory<MainPresenter> {
            override fun create(): MainPresenter {
                return MainPresenterImpl(interactor)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initSwipeRefreshLayout(view.find<SwipeRefreshLayout>(R.id.swipe_refresh_layout))
        initRecyclerView(view.find<RecyclerView>(R.id.recycler_view))

        return view
    }

    private fun initSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.colorAccent)
            setOnRefreshListener {
                presenter?.getPokemonList()
            }
        }
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        pokemonRecyclerViewAdapter = PokemonRecyclerViewAdapter { (id, name) ->
            startActivity<DetailsActivity>(
                    DetailsActivity.EXTRA_POKEMON_ID to id,
                    DetailsActivity.EXTRA_POKEMON_NAME to name
            )
        }
        recyclerView.apply {
            adapter = pokemonRecyclerViewAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    // MainView

    override fun showLoadingIndicator() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun hideLoadingIndicator() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun showPokemonList(pokemonList: List<Pokemon>) {
        pokemonRecyclerViewAdapter.pokemonList = pokemonList
    }

    override fun showDoneMessage() {
        Snackbar.make(activity.coordinator_layout, R.string.done, Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun showErrorMessage() {
        Snackbar.make(activity.coordinator_layout, R.string.error, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    presenter?.getPokemonList()
                }
                .show()
    }

}
