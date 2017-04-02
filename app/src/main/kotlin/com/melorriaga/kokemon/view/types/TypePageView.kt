package com.melorriaga.kokemon.view.types

import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.view.base.BaseView

interface TypePageView : BaseView {

    fun showLoadingIndicator()
    fun hideLoadingIndicator()
    fun getPokemonTypeId() : Int
    fun showAllPokemonOfType(pokemonList: List<Pokemon>)
    fun showDoneMessage()
    fun showErrorMessage()

}
