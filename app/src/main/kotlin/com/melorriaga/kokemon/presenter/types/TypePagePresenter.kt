package com.melorriaga.kokemon.presenter.types

import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.view.types.TypePageView

interface TypePagePresenter : BasePresenter<TypePageView> {

    fun getAllPokemonOfType(id: Int)

}
