package com.melorriaga.kokemon.interactor

import com.melorriaga.kokemon.model.api.PokemonService
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainInteractorImpl(private val pokemonService: PokemonService) : MainInteractor {

    private var subscription: Subscription? = null

    override var networkRequestInProgress = false

    override fun getPokemonNames(listener: MainInteractor.OnGetPokemonNamesListener) {
        networkRequestInProgress = true
        subscription = pokemonService.getPokemonNames(150)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.results.map { it.name } }
                .subscribe({ next ->
                    networkRequestInProgress = false
                    listener.onSuccess(next)
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
