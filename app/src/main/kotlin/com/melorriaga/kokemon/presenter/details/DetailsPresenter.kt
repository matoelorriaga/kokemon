package com.melorriaga.kokemon.presenter.details

import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.view.details.DetailsView

interface DetailsPresenter : BasePresenter<DetailsView> {

    fun getPokemonDetails(id: Int)

}
