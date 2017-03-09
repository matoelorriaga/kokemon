package com.melorriaga.kokemon.interactor

import com.melorriaga.kokemon.interactor.base.BaseInteractor
import com.melorriaga.kokemon.model.Pokemon

interface DetailsInteractor : BaseInteractor {

    interface OnGetPokemonDetailsListener {
        fun onSuccess(pokemon: Pokemon)
        fun onFailure()
    }

    var networkRequestInProgress: Boolean

    fun getPokemonDetails(id: Int, listener: OnGetPokemonDetailsListener)
    fun cancelNetworkRequest()

}
