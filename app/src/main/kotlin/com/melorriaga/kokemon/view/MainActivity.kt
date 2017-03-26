package com.melorriaga.kokemon.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.app
import com.melorriaga.kokemon.extension.initToolbar
import com.melorriaga.kokemon.injection.module.MainModule
import com.melorriaga.kokemon.interactor.MainInteractor
import com.melorriaga.kokemon.presenter.MainPresenter
import com.melorriaga.kokemon.presenter.MainPresenterImpl
import com.melorriaga.kokemon.presenter.loader.PresenterFactory
import com.melorriaga.kokemon.view.adapter.PokemonRecyclerViewAdapter
import com.melorriaga.kokemon.view.base.BaseRetainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class MainActivity : BaseRetainActivity<MainPresenter, MainView>(), MainView {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initSwipeRefreshLayout()
        initRecyclerView()
    }

    private fun initSwipeRefreshLayout() {
        swipe_refresh_layout.apply {
            setColorSchemeResources(R.color.colorAccent)
            setOnRefreshListener {
                presenter?.getPokemonNames()
            }
        }
    }

    private fun initRecyclerView() {
        pokemonRecyclerViewAdapter = PokemonRecyclerViewAdapter { position, pokemonName ->
            startActivity(intentFor<DetailsActivity>(
                    DetailsActivity.EXTRA_POKEMON_ID to position + 1,
                    DetailsActivity.EXTRA_POKEMON_NAME to pokemonName
            ))
        }
        recycler_view.apply {
            adapter = pokemonRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // MainView

    override fun showLoadingIndicator() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun hideLoadingIndicator() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun showPokemonNames(pokemonNames: List<String>) {
        pokemonRecyclerViewAdapter.pokemonNames = pokemonNames
    }

    override fun showDoneMessage() {
        Snackbar.make(coordinator_layout, R.string.done, Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun showErrorMessage() {
        Snackbar.make(coordinator_layout, R.string.error, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    presenter?.getPokemonNames()
                }
                .show()
    }

}
