package com.melorriaga.kokemon.interactor

import com.melorriaga.kokemon.interactor.base.BaseInteractor
import com.melorriaga.kokemon.model.Type

interface TypesInteractor : BaseInteractor {

    interface OnGetPokemonTypesListener {
        fun onSuccess(pokemonTypes: List<Type>)
        fun onFailure()
    }

    var networkRequestInProgress: Boolean

    fun getPokemonTypes(listener: OnGetPokemonTypesListener)
    fun cancelNetworkRequest()

}
