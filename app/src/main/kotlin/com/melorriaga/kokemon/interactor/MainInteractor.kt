package com.melorriaga.kokemon.interactor

import com.melorriaga.kokemon.interactor.base.BaseInteractor

interface MainInteractor : BaseInteractor {

    interface OnGetPokemonNamesListener {
        fun onSuccess(pokemonNames: List<String>)
        fun onFailure()
    }

    var networkRequestInProgress: Boolean

    fun getPokemonNames(listener: OnGetPokemonNamesListener)
    fun cancelNetworkRequest()

}
