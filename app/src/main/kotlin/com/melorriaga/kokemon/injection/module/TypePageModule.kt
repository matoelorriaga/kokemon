package com.melorriaga.kokemon.injection.module

import com.melorriaga.kokemon.injection.scope.FragmentScope
import com.melorriaga.kokemon.interactor.TypePageInteractor
import com.melorriaga.kokemon.interactor.TypePageInteractorImpl
import com.melorriaga.kokemon.model.api.PokemonService
import dagger.Module
import dagger.Provides

@Module
class TypePageModule {

    @Provides
    @FragmentScope
    fun provideTypePageInteractor(pokemonService: PokemonService): TypePageInteractor {
        return TypePageInteractorImpl(pokemonService)
    }

}
