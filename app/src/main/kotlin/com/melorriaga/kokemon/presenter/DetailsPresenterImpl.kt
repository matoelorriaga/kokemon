package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.DetailsInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.base.BasePresenterImpl
import com.melorriaga.kokemon.view.details.DetailsView

class DetailsPresenterImpl(private val interactor: DetailsInteractor) :
        BasePresenterImpl<DetailsView>(), DetailsPresenter, DetailsInteractor.OnGetPokemonDetailsListener {

    private var pokemon: Pokemon? = null

    override fun onStart(firstStart: Boolean) {
        if (firstStart) {
            view?.getPokemonId()?.let {
                getPokemonDetails(it)
            }
        } else {
            if (interactor.networkRequestInProgress) {
                view?.showLoadingIndicator()
            }
            pokemon?.let {
                view?.showPokemonDetails(it)
            }
        }
    }

    override fun onStop() {
        // do nothing
    }

    override fun onPresenterDestroyed() {
        interactor.cancelNetworkRequest()
    }

    // DetailsPresenter

    override fun getPokemonDetails(id: Int) {
        view?.showLoadingIndicator()
        interactor.getPokemonDetails(id, this)
    }

    // OnGetPokemonDetailsListener

    override fun onSuccess(pokemon: Pokemon) {
        this.pokemon = pokemon

        view?.showPokemonDetails(pokemon)
        view?.hideLoadingIndicator()
        view?.showDoneMessage()
    }

    override fun onFailure() {
        view?.hideLoadingIndicator()
        view?.showErrorMessage()
    }

}
