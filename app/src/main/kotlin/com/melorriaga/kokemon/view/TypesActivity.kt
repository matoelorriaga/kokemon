package com.melorriaga.kokemon.view

import android.os.Bundle
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.initToolbar
import com.melorriaga.kokemon.view.base.BaseActivity
import kotlinx.android.synthetic.main.template_toolbar.*

class TypesActivity : BaseActivity(), TypesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)

        initToolbar(hasParent = true)
        toolbar.elevation = 0F
    }

}
