package com.melorriaga.kokemon.presenter.main

import com.melorriaga.kokemon.interactor.main.MainInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.base.BasePresenterImpl
import com.melorriaga.kokemon.view.main.MainView

class MainPresenterImpl(private val interactor: MainInteractor) :
        BasePresenterImpl<MainView>(), MainPresenter, MainInteractor.OnGetPokemonListListener {

    private var pokemonList = listOf<Pokemon>()

    override fun onStart(firstStart: Boolean) {
        if (firstStart) {
            getPokemonList()
        } else {
            if (interactor.networkRequestInProgress) {
                view?.showLoadingIndicator()
            }
            view?.showPokemonList(pokemonList)
        }
    }

    override fun onStop() {
        view?.hideLoadingIndicator()
    }

    override fun onPresenterDestroyed() {
        interactor.cancelNetworkRequest()
    }

    // MainPresenter

    override fun getPokemonList() {
        view?.showLoadingIndicator()
        interactor.getPokemonList(this)
    }

    // OnGetPokemonListListener

    override fun onSuccess(pokemonList: List<Pokemon>) {
        this.pokemonList = pokemonList

        view?.showPokemonList(pokemonList)
        view?.hideLoadingIndicator()
        view?.showDoneMessage()
    }

    override fun onFailure() {
        view?.hideLoadingIndicator()
        view?.showErrorMessage()
    }

}
