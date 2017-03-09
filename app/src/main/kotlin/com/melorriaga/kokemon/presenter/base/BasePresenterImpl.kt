package com.melorriaga.kokemon.presenter.base

import com.melorriaga.kokemon.view.base.BaseView

abstract class BasePresenterImpl<V: BaseView> : BasePresenter<V> {

    protected var view: V? = null

    override fun onViewAttached(view: V) {
        this.view = view
    }

    override fun onViewDetached() {
        this.view = null
    }

}
