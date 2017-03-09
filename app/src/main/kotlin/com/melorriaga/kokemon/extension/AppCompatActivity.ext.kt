package com.melorriaga.kokemon.extension

import android.support.v7.app.AppCompatActivity
import com.melorriaga.kokemon.KokemonApp
import kotlinx.android.synthetic.main.template_toolbar.*

val AppCompatActivity.app: KokemonApp
    get() = application as KokemonApp

fun AppCompatActivity.initToolbar(title: String? = null, hasParent: Boolean = false) {
    setSupportActionBar(toolbar)

    // set title (if needed)
    title?.let {
        supportActionBar?.title = it
    }

    // set back arrow (if needed)
    if (hasParent) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
