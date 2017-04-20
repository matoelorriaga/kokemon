package com.melorriaga.kokemon.view.types

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout.MODE_SCROLLABLE
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.app
import com.melorriaga.kokemon.extension.initToolbar
import com.melorriaga.kokemon.injection.module.TypesModule
import com.melorriaga.kokemon.interactor.types.TypesInteractor
import com.melorriaga.kokemon.model.Type
import com.melorriaga.kokemon.presenter.loader.PresenterFactory
import com.melorriaga.kokemon.presenter.types.TypesPresenter
import com.melorriaga.kokemon.presenter.types.TypesPresenterImpl
import com.melorriaga.kokemon.view.base.BaseRetainActivity
import kotlinx.android.synthetic.main.activity_types.*
import kotlinx.android.synthetic.main.template_tab_layout.*
import kotlinx.android.synthetic.main.template_toolbar.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class TypesActivity : BaseRetainActivity<TypesPresenter, TypesView>(), TypesView {

    @Inject
    lateinit var interactor: TypesInteractor

    lateinit var typesFragmentPagerAdapter: TypesFragmentPagerAdapter

    lateinit var progressDialog: ProgressDialog

    override fun injectDependencies() {
        app.applicationComponent
                .plus(TypesModule())
                .injectTo(this)
    }

    override fun presenterFactory(): PresenterFactory<TypesPresenter> {
        return object : PresenterFactory<TypesPresenter> {
            override fun create(): TypesPresenter {
                return TypesPresenterImpl(interactor)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)

        initToolbar()
        initViewPager()
        initTabLayout()
        initProgressDialog()
    }

    private fun initToolbar() {
        initToolbar(hasParent = true)
        toolbar.elevation = 0F
    }

    private fun initViewPager() {
        typesFragmentPagerAdapter = TypesFragmentPagerAdapter(supportFragmentManager)
        view_pager.adapter = typesFragmentPagerAdapter
    }

    private fun initTabLayout() {
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.tabMode = MODE_SCROLLABLE
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this).apply {
            setMessage(getString(R.string.loading))
            setCancelable(false)
            isIndeterminate = true
        }
    }

    // TypesView

    override fun showLoadingIndicator() {
        progressDialog.show()
    }

    override fun hideLoadingIndicator() {
        progressDialog.dismiss()
    }

    override fun showPokemonTypes(pokemonTypes: List<Type>) {
        typesFragmentPagerAdapter.fragments = pokemonTypes.map {
            TypePageFragment().withArguments(
                    TypePageFragment.ARG_TYPE_ID to it.id,
                    TypePageFragment.ARG_TYPE_NAME to it.name
            )
        }
    }

    override fun showDoneMessage() {
        Snackbar.make(coordinator_layout, R.string.done, Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun showErrorMessage() {
        Snackbar.make(coordinator_layout, R.string.error, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    presenter?.getPokemonTypes()
                }
                .show()
    }

    // https://github.com/matoelorriaga/kokemon/issues/1

    private var selectedTabPosition: Int? = null

    override fun onStop() {
        selectedTabPosition = tab_layout.selectedTabPosition

        super.onStop()
    }

    override fun onStart() {
        super.onStart()

        selectedTabPosition?.let {
            Handler().post {
                tab_layout.getTabAt(it)?.select()
            }
        }
    }

}
