package com.melorriaga.kokemon.presenter.loader

import android.content.Context
import android.support.v4.content.Loader
import com.melorriaga.kokemon.presenter.base.BasePresenter

class PresenterLoader<P: BasePresenter<*>>(
        context: Context, private val factory: PresenterFactory<P>) : Loader<P>(context) {

    private var presenter: P? = null

    override fun onStartLoading() {
        if (presenter != null) {
            deliverResult(presenter)
            return
        }
        forceLoad()
    }

    override fun onForceLoad() {
        presenter = factory.create()
        deliverResult(presenter)
    }

    override fun onReset() {
        if (presenter != null) {
            presenter?.onPresenterDestroyed()
            presenter = null
        }
    }

}
