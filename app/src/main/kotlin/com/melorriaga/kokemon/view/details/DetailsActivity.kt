package com.melorriaga.kokemon.view.details

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.app
import com.melorriaga.kokemon.extension.initToolbar
import com.melorriaga.kokemon.injection.module.DetailsModule
import com.melorriaga.kokemon.interactor.DetailsInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.DetailsPresenter
import com.melorriaga.kokemon.presenter.DetailsPresenterImpl
import com.melorriaga.kokemon.presenter.loader.PresenterFactory
import com.melorriaga.kokemon.view.base.BaseRetainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseRetainActivity<DetailsPresenter, DetailsView>(), DetailsView {

    companion object {
        val EXTRA_POKEMON_ID = "EXTRA_POKEMON_ID"
        val EXTRA_POKEMON_NAME = "EXTRA_POKEMON_NAME"
    }

    @Inject
    lateinit var interactor: DetailsInteractor

    override fun injectDependencies() {
        app.applicationComponent
                .plus(DetailsModule())
                .injectTo(this)
    }

    override fun presenterFactory(): PresenterFactory<DetailsPresenter> {
        return object : PresenterFactory<DetailsPresenter> {
            override fun create(): DetailsPresenter {
                return DetailsPresenterImpl(interactor)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val pokemonId = intent.getIntExtra(EXTRA_POKEMON_ID, 0)
        val pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME)

        initToolbar("$pokemonName (#$pokemonId)", true)
    }

    // DetailsView

    override fun showLoadingIndicator() {
        progress_bar.visibility = View.VISIBLE
        pokemon_details_layout.visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        progress_bar.visibility = View.GONE
        pokemon_details_layout.visibility = View.VISIBLE
    }

    override fun getPokemonId() = intent.getIntExtra(EXTRA_POKEMON_ID, 0)

    override fun showPokemonDetails(pokemon: Pokemon) {
        Picasso.with(this).load(pokemon.sprites.frontDefault).into(pokemon_front_image_view)
        Picasso.with(this).load(pokemon.sprites.backDefault).into(pokemon_back_image_view)
        pokemon_id_text_view.text = resources.getString(R.string.pokemon_id, pokemon.id)
        pokemon_name_text_view.text = resources.getString(R.string.pokemon_name, pokemon.name)
        pokemon_height_text_view.text = resources.getString(R.string.pokemon_height, pokemon.height)
        pokemon_weight_text_view.text = resources.getString(R.string.pokemon_weight, pokemon.weight)
        pokemon_base_experience_text_view.text = resources.getString(R.string.pokemon_base_experience, pokemon.baseExperience)
    }

    override fun showDoneMessage() {
        Snackbar.make(coordinator_layout, R.string.done, Snackbar.LENGTH_SHORT).show()
    }

    override fun showErrorMessage() {
        Snackbar.make(coordinator_layout, R.string.error, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    presenter?.getPokemonDetails(getPokemonId())
                }
                .show()
    }

}
