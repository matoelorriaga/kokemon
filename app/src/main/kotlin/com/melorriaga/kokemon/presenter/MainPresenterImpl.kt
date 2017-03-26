package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.MainInteractor
import com.melorriaga.kokemon.presenter.base.BasePresenterImpl
import com.melorriaga.kokemon.view.main.MainView

class MainPresenterImpl(private val interactor: MainInteractor) :
        BasePresenterImpl<MainView>(), MainPresenter, MainInteractor.OnGetPokemonNamesListener {

    private var pokemonNames = listOf<String>()

    override fun onStart(firstStart: Boolean) {
        if (firstStart) {
            getPokemonNames()
        } else {
            if (interactor.networkRequestInProgress) {
                view?.showLoadingIndicator()
            }
            view?.showPokemonNames(pokemonNames)
        }
    }

    override fun onStop() {
        // do nothing
    }

    override fun onPresenterDestroyed() {
        interactor.cancelNetworkRequest()
    }

    // MainPresenter

    override fun getPokemonNames() {
        view?.showLoadingIndicator()
        interactor.getPokemonNames(this)
    }

    // OnGetPokemonNamesListener

    override fun onSuccess(pokemonNames: List<String>) {
        this.pokemonNames = pokemonNames

        view?.showPokemonNames(pokemonNames)
        view?.hideLoadingIndicator()
        view?.showDoneMessage()
    }

    override fun onFailure() {
        view?.hideLoadingIndicator()
        view?.showErrorMessage()
    }

}
