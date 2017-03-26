package com.melorriaga.kokemon.view.main

import com.melorriaga.kokemon.view.base.BaseView

interface MainView : BaseView {

    fun showLoadingIndicator()
    fun hideLoadingIndicator()
    fun showPokemonNames(pokemonNames: List<String>)
    fun showDoneMessage()
    fun showErrorMessage()

}
