package com.melorriaga.kokemon.view.main

import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.view.base.BaseView

interface MainView : BaseView {

    fun showLoadingIndicator()
    fun hideLoadingIndicator()
    fun showPokemonList(pokemonList: List<Pokemon>)
    fun showDoneMessage()
    fun showErrorMessage()

}
