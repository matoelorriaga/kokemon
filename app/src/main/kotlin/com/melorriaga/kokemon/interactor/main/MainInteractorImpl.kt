package com.melorriaga.kokemon.interactor.main

import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.model.api.PokemonService
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainInteractorImpl(private val pokemonService: PokemonService) : MainInteractor {

    private var subscription: Subscription? = null

    override var networkRequestInProgress = false

    override fun getPokemonList(listener: MainInteractor.OnGetPokemonListListener) {
        networkRequestInProgress = true
        subscription = pokemonService.getPokemonList(150)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.results.map {
                        val pokemonId = it.url.split("/")[6].toInt()
                        Pokemon(pokemonId, it.name)
                    }
                }
                .subscribe({ pokemonList ->
                    networkRequestInProgress = false
                    listener.onSuccess(pokemonList)
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
