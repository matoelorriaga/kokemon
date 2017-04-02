package com.melorriaga.kokemon.injection.module

import com.melorriaga.kokemon.injection.scope.ActivityScope
import com.melorriaga.kokemon.injection.scope.FragmentScope
import com.melorriaga.kokemon.interactor.details.DetailsInteractor
import com.melorriaga.kokemon.interactor.details.DetailsInteractorImpl
import com.melorriaga.kokemon.model.api.PokemonService
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @Provides
    @ActivityScope
    fun provideDetailsInteractor(pokemonService: PokemonService): DetailsInteractor {
        return DetailsInteractorImpl(pokemonService)
    }

}
