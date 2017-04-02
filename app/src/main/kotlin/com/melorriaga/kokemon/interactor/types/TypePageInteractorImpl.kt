package com.melorriaga.kokemon.interactor.types

import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.model.api.PokemonService
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TypePageInteractorImpl(private val pokemonService: PokemonService) : TypePageInteractor {

    private var subscription: Subscription? = null

    override var networkRequestInProgress = false

    override fun getAllPokemonOfType(id: Int, listener: TypePageInteractor.OnGetAllPokemonOfTypeListener) {
        networkRequestInProgress = true
        subscription = pokemonService.getPokemonType(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.pokemon.map {
                        val pokemonId = it.pokemon.url.split("/")[6].toInt()
                        Pokemon(pokemonId, it.pokemon.name)
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
