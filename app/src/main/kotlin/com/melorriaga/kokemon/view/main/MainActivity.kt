package com.melorriaga.kokemon.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.initToolbar
import com.melorriaga.kokemon.view.base.BaseActivity
import com.melorriaga.kokemon.view.types.TypesActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        var mainFragment = supportFragmentManager.findFragmentByTag(MainFragment.TAG)
        if (mainFragment == null) {
            mainFragment = MainFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, mainFragment, MainFragment.TAG)
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.types -> {
                startActivity<TypesActivity>()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
