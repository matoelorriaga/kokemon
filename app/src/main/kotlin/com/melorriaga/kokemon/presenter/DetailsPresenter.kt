package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.view.DetailsView

interface DetailsPresenter : BasePresenter<DetailsView> {

    fun getPokemonDetails(id: Int)

}
