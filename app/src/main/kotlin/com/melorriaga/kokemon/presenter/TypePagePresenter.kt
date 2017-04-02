package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.view.types.TypePageView

interface TypePagePresenter : BasePresenter<TypePageView> {

    fun getAllPokemonOfType(id: Int)

}
