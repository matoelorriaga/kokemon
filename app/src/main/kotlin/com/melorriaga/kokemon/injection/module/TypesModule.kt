package com.melorriaga.kokemon.injection.module

import com.melorriaga.kokemon.injection.scope.ActivityScope
import com.melorriaga.kokemon.interactor.types.TypesInteractor
import com.melorriaga.kokemon.interactor.types.TypesInteractorImpl
import com.melorriaga.kokemon.model.api.PokemonService
import dagger.Module
import dagger.Provides

@Module
class TypesModule {

    @Provides
    @ActivityScope
    fun provideTypesInteractor(pokemonService: PokemonService): TypesInteractor {
        return TypesInteractorImpl(pokemonService)
    }

}
