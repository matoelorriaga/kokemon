package com.melorriaga.kokemon.interactor.types

import com.melorriaga.kokemon.model.Type
import com.melorriaga.kokemon.model.api.PokemonService
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TypesInteractorImpl(private val pokemonService: PokemonService) : TypesInteractor {

    private var subscription: Subscription? = null

    override var networkRequestInProgress = false

    override fun getPokemonTypes(listener: TypesInteractor.OnGetPokemonTypesListener) {
        networkRequestInProgress = true
        subscription = pokemonService.getPokemonTypes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.results.map {
                        val typeId = it.url.split("/")[6].toInt()
                        Type(typeId, it.name)
                    }
                }
                .subscribe({ pokemonTypes ->
                    networkRequestInProgress = false
                    listener.onSuccess(pokemonTypes)
                }, { error ->
                    error.printStackTrace()
                    networkRequestInProgress = false
                    listener.onFailure()
                })
    }

    override fun cancelNetworkRequest() {
        subscription?.unsubscribe()
    }

}
