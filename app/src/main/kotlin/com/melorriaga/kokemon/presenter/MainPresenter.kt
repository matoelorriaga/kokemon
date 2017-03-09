package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.view.MainView

interface MainPresenter : BasePresenter<MainView> {

    fun getPokemonNames()

}
