package com.melorriaga.kokemon.interactor

import com.melorriaga.kokemon.model.api.PokemonService
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailsInteractorImpl(private val pokemonService: PokemonService) : DetailsInteractor {

    private var subscription: Subscription? = null

    override var networkRequestInProgress = false

    override fun getPokemonDetails(id: Int, listener: DetailsInteractor.OnGetPokemonDetailsListener) {
        networkRequestInProgress = true
        subscription = pokemonService.getPokemonDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ pokemon ->
                    networkRequestInProgress = false
                    listener.onSuccess(pokemon)
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
