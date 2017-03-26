package com.melorriaga.kokemon.view.base

import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.melorriaga.kokemon.R
import org.jetbrains.anko.find

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_base, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                about()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun about() {
        Snackbar.make(find<CoordinatorLayout>(R.id.coordinator_layout), R.string.about_message, Snackbar.LENGTH_SHORT)
                .show()
    }

}
