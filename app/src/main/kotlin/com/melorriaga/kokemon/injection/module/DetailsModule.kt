package com.melorriaga.kokemon.injection.module

import com.melorriaga.kokemon.injection.scope.FragmentScope
import com.melorriaga.kokemon.interactor.DetailsInteractor
import com.melorriaga.kokemon.interactor.DetailsInteractorImpl
import com.melorriaga.kokemon.model.api.PokemonService
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @Provides
    @FragmentScope
    fun provideDetailsInteractor(pokemonService: PokemonService): DetailsInteractor {
        return DetailsInteractorImpl(pokemonService)
    }

}
