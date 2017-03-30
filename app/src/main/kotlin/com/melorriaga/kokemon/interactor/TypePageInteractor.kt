package com.melorriaga.kokemon.interactor

import com.melorriaga.kokemon.interactor.base.BaseInteractor
import com.melorriaga.kokemon.model.Pokemon

interface TypePageInteractor : BaseInteractor {

    interface OnGetAllPokemonOfTypeListener {
        fun onSuccess(pokemonList: List<Pokemon>)
        fun onFailure()
    }

    var networkRequestInProgress: Boolean

    fun getAllPokemonOfType(typeId: Int, listener: OnGetAllPokemonOfTypeListener)
    fun cancelNetworkRequest()

}
