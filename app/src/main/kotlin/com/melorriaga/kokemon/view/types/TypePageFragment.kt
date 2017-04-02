package com.melorriaga.kokemon.view.types

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
import com.melorriaga.kokemon.injection.module.TypePageModule
import com.melorriaga.kokemon.interactor.types.TypePageInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.types.TypePagePresenter
import com.melorriaga.kokemon.presenter.types.TypePagePresenterImpl
import com.melorriaga.kokemon.presenter.loader.PresenterFactory
import com.melorriaga.kokemon.view.base.BaseRetainFragment
import com.melorriaga.kokemon.view.common.PokemonRecyclerViewAdapter
import com.melorriaga.kokemon.view.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_types.*
import kotlinx.android.synthetic.main.fragment_type_page.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class TypePageFragment : BaseRetainFragment<TypePagePresenter, TypePageView>(), TypePageView {

    companion object {
        val ARG_TYPE_ID = "ARG_TYPE_ID"
        val ARG_TYPE_NAME = "ARG_TYPE_NAME"
    }

    @Inject
    lateinit var interactor: TypePageInteractor

    lateinit var pokemonRecyclerViewAdapter: PokemonRecyclerViewAdapter

    override fun injectDependencies() {
        app.applicationComponent
                .plus(TypePageModule())
                .injectTo(this)
    }

    override fun presenterFactory(): PresenterFactory<TypePagePresenter> {
        return object : PresenterFactory<TypePagePresenter> {
            override fun create(): TypePagePresenter {
                return TypePagePresenterImpl(interactor)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_type_page, container, false)

        view.find<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setColorSchemeResources(R.color.colorAccent)
            setOnRefreshListener {
                presenter?.getAllPokemonOfType(getPokemonTypeId())
            }
        }

        pokemonRecyclerViewAdapter = PokemonRecyclerViewAdapter { (id, name) ->
            startActivity<DetailsActivity>(
                    DetailsActivity.EXTRA_POKEMON_ID to id,
                    DetailsActivity.EXTRA_POKEMON_NAME to name
            )
        }

        view.find<RecyclerView>(R.id.recycler_view).apply {
            adapter = pokemonRecyclerViewAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        return view
    }

    // TypePageView

    override fun showLoadingIndicator() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun hideLoadingIndicator() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun getPokemonTypeId() = arguments.getInt(TypePageFragment.ARG_TYPE_ID)

    override fun showAllPokemonOfType(pokemonList: List<Pokemon>) {
        pokemonRecyclerViewAdapter.pokemonList = pokemonList
    }

    override fun showDoneMessage() {
        Snackbar.make(activity.coordinator_layout, R.string.done, Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun showErrorMessage() {
        Snackbar.make(activity.coordinator_layout, R.string.error, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    presenter?.getAllPokemonOfType(getPokemonTypeId())
                }
                .show()
    }

}
