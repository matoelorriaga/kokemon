package com.melorriaga.kokemon.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.initToolbar

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val pokemonId = intent.getIntExtra(MainActivity.EXTRA_POKEMON_ID, 0)
        val pokemonName = intent.getStringExtra(MainActivity.EXTRA_POKEMON_NAME)

        initToolbar("$pokemonName (#$pokemonId)", true)

        var detailsFragment = supportFragmentManager.findFragmentByTag(DetailsFragment.TAG)
        if (detailsFragment == null) {
            detailsFragment = DetailsFragment.newInstance(pokemonId, pokemonName)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, detailsFragment, DetailsFragment.TAG)
                    .commit()
        }
    }

}
