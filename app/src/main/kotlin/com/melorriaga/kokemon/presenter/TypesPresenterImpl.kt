package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.TypesInteractor
import com.melorriaga.kokemon.model.Type
import com.melorriaga.kokemon.presenter.base.BasePresenterImpl
import com.melorriaga.kokemon.view.types.TypesView

class TypesPresenterImpl(private val interactor: TypesInteractor) :
        BasePresenterImpl<TypesView>(), TypesPresenter, TypesInteractor.OnGetPokemonTypesListener {

    private var pokemonTypes = listOf<Type>()

    override fun onStart(firstStart: Boolean) {
        if (firstStart) {
            getPokemonTypes()
        } else {
            if (interactor.networkRequestInProgress) {
                view?.showLoadingIndicator()
            }
            view?.showPokemonTypes(pokemonTypes)
        }
    }

    override fun onStop() {
        view?.hideLoadingIndicator()
    }

    override fun onPresenterDestroyed() {
        interactor.cancelNetworkRequest()
    }

    // TypesPresenter

    override fun getPokemonTypes() {
        view?.showLoadingIndicator()
        interactor.getPokemonTypes(this)
    }

    // OnGetPokemonTypesListener

    override fun onSuccess(pokemonTypes: List<Type>) {
        this.pokemonTypes = pokemonTypes

        view?.showPokemonTypes(pokemonTypes)
        view?.hideLoadingIndicator()
        view?.showDoneMessage()
    }

    override fun onFailure() {
        view?.hideLoadingIndicator()
        view?.showErrorMessage()
    }

}
