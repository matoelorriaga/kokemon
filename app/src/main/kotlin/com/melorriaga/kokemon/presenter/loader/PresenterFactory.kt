package com.melorriaga.kokemon.presenter.loader

import com.melorriaga.kokemon.presenter.base.BasePresenter

interface PresenterFactory<out P: BasePresenter<*>> {

    fun create(): P

}
