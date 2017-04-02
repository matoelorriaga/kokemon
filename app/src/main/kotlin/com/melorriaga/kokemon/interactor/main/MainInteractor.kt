package com.melorriaga.kokemon.interactor.main

import com.melorriaga.kokemon.interactor.base.BaseInteractor
import com.melorriaga.kokemon.model.Pokemon

interface MainInteractor : BaseInteractor {

    interface OnGetPokemonListListener {
        fun onSuccess(pokemonList: List<Pokemon>)
        fun onFailure()
    }

    var networkRequestInProgress: Boolean

    fun getPokemonList(listener: OnGetPokemonListListener)
    fun cancelNetworkRequest()

}
