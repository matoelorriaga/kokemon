package com.melorriaga.kokemon.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.initToolbar
import org.jetbrains.anko.support.v4.withArguments

class DetailsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_POKEMON_ID = "EXTRA_POKEMON_ID"
        val EXTRA_POKEMON_NAME = "EXTRA_POKEMON_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val pokemonId = intent.getIntExtra(EXTRA_POKEMON_ID, 0)
        val pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME)

        initToolbar("$pokemonName (#$pokemonId)", true)

        var detailsFragment = supportFragmentManager.findFragmentByTag(DetailsFragment.TAG)
        if (detailsFragment == null) {
            detailsFragment = DetailsFragment().withArguments(
                    DetailsFragment.ARG_POKEMON_ID to pokemonId,
                    DetailsFragment.ARG_POKEMON_NAME to pokemonName
            )
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, detailsFragment, DetailsFragment.TAG)
                    .commit()
        }
    }

}
