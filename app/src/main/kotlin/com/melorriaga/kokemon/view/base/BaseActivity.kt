package com.melorriaga.kokemon.view.base

import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import com.melorriaga.kokemon.presenter.base.BasePresenter
import com.melorriaga.kokemon.presenter.loader.PresenterFactory
import com.melorriaga.kokemon.presenter.loader.PresenterLoader
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseActivity<P: BasePresenter<V>, in V>
    : AppCompatActivity(), BaseView, LoaderManager.LoaderCallbacks<P> {

    companion object {
        val FIRST_TIME = "FIRST_TIME"
        val LOADER_ID = 0
    }

    protected var presenter: P? = null

    /**
     * True if this is the first time the activity is created.
     * Used to avoid unnecessary calls after activity recreation.
     */
    private var firstTime = true

    /**
     * True if presenter is null (not loaded yet) when [onStart] is called.
     * Used to make sure presenter is available before start working.
     */
    private var needToCallStart = AtomicBoolean(false)

    abstract fun injectDependencies()

    abstract fun presenterFactory(): PresenterFactory<P>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            firstTime = it.getBoolean(FIRST_TIME)
        }

        injectDependencies()

        supportLoaderManager.initLoader(LOADER_ID, Bundle.EMPTY, this).startLoading()
    }

    override fun onStart() {
        super.onStart()

        if (presenter == null) {
            needToCallStart.set(true)
        } else {
            doStart()
        }
    }

    private fun doStart() {
        presenter?.onViewAttached(this as V)
        presenter?.onStart(firstTime)

        firstTime = false
    }

    override fun onStop() {
        if (presenter != null) {
            presenter?.onStop()
            presenter?.onViewDetached()
        }

        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(FIRST_TIME, firstTime)
    }

    // LoaderCallbacks

    override fun onCreateLoader(id: Int, args: Bundle): Loader<P> {
        return PresenterLoader(this, presenterFactory())
    }

    override fun onLoadFinished(loader: Loader<P>, data: P) {
        presenter = data

        if (needToCallStart.compareAndSet(true, false)) {
            doStart()
        }
    }

    override fun onLoaderReset(loader: Loader<P>) {
        presenter = null
    }

}
