package com.melorriaga.kokemon.view.types

import com.melorriaga.kokemon.model.Type
import com.melorriaga.kokemon.view.base.BaseView

interface TypesView : BaseView {

    fun showLoadingIndicator()
    fun hideLoadingIndicator()
    fun showPokemonTypes(pokemonTypes: List<Type>)
    fun showDoneMessage()
    fun showErrorMessage()

}
