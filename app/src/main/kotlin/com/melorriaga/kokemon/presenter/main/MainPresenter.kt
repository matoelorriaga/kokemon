package com.melorriaga.kokemon.presenter.main

import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.view.main.MainView

interface MainPresenter : BasePresenter<MainView> {

    fun getPokemonList()

}
