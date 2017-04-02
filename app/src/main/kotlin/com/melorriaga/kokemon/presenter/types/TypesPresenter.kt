package com.melorriaga.kokemon.presenter.types

import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.view.types.TypesView

interface TypesPresenter : BasePresenter<TypesView> {

    fun getPokemonTypes()

}
