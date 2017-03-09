package com.melorriaga.kokemon.presenter.base

interface BasePresenter<in V> {

    /**
     * Called when the view is attached to the presenter.
     *
     * @param view the view.
     */
    fun onViewAttached(view: V)

    /**
     * Called when the view is detached from the presenter.
     */
    fun onViewDetached()

    /**
     * Called every time the view starts.
     * The view is guarantee to be not null starting at this method, until [onStop] is called.
     *
     * @param firstStart true if it's the first start of the presenter, only once in the presenter lifetime.
     */
    fun onStart(firstStart: Boolean)

    /**
     * Called every time the view stops.
     * After this method, the view will be null until next [onStart] call.
     */
    fun onStop()

    /**
     * Called when the presenter is definitely destroyed.
     * Use this method only to release any resource used by the presenter (cancel HTTP requests, close database connections, etc.).
     */
    fun onPresenterDestroyed()

}
