package com.melorriaga.kokemon.view.details

import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.view.base.BaseView

interface DetailsView : BaseView {

    fun showLoadingIndicator()
    fun hideLoadingIndicator()
    fun getPokemonId(): Int
    fun showPokemonDetails(pokemon: Pokemon)
    fun showDoneMessage()
    fun showErrorMessage()

}
