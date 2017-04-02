package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.TypePageInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.base.BasePresenterImpl
import com.melorriaga.kokemon.view.types.TypePageView

class TypePagePresenterImpl(private val interactor: TypePageInteractor) :
        BasePresenterImpl<TypePageView>(), TypePagePresenter, TypePageInteractor.OnGetAllPokemonOfTypeListener {

    private var pokemonList = listOf<Pokemon>()

    override fun onStart(firstStart: Boolean) {
        if (firstStart) {
            view?.getPokemonTypeId()?.let {
                getAllPokemonOfType(it)
            }
        } else {
            if (interactor.networkRequestInProgress) {
                view?.showLoadingIndicator()
            }
            view?.showAllPokemonOfType(pokemonList)
        }
    }

    override fun onStop() {
        view?.hideLoadingIndicator()
    }

    override fun onPresenterDestroyed() {
        interactor.cancelNetworkRequest()
    }

    // TypePagePresenter

    override fun getAllPokemonOfType(id: Int) {
        view?.showLoadingIndicator()
        interactor.getAllPokemonOfType(id, this)
    }

    // OnGetAllPokemonOfTypeListener

    override fun onSuccess(pokemonList: List<Pokemon>) {
        this.pokemonList = pokemonList

        view?.showAllPokemonOfType(pokemonList)
        view?.hideLoadingIndicator()
        view?.showDoneMessage()
    }

    override fun onFailure() {
        view?.hideLoadingIndicator()
        view?.showErrorMessage()
    }

}
