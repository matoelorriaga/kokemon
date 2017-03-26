package com.melorriaga.kokemon.view.details

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.extension.app
import com.melorriaga.kokemon.injection.module.DetailsModule
import com.melorriaga.kokemon.interactor.DetailsInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.DetailsPresenter
import com.melorriaga.kokemon.presenter.DetailsPresenterImpl
import com.melorriaga.kokemon.presenter.loader.PresenterFactory
import com.melorriaga.kokemon.view.base.BaseRetainFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : BaseRetainFragment<DetailsPresenter, DetailsView>(), DetailsView {

    companion object {
        val TAG = "DetailsFragment"
        val ARG_POKEMON_ID = "ARG_POKEMON_ID"
        val ARG_POKEMON_NAME = "ARG_POKEMON_NAME"
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_details, container, false)

    // DetailsView

    override fun showLoadingIndicator() {
        progress_bar.visibility = VISIBLE
        pokemon_details_layout.visibility = GONE
    }

    override fun hideLoadingIndicator() {
        progress_bar.visibility = GONE
        pokemon_details_layout.visibility = VISIBLE
    }

    override fun getPokemonId() = arguments.getInt(ARG_POKEMON_ID)

    override fun showPokemonDetails(pokemon: Pokemon) {
        Picasso.with(activity).load(pokemon.sprites.frontDefault).into(pokemon_front_image_view)
        Picasso.with(activity).load(pokemon.sprites.backDefault).into(pokemon_back_image_view)
        pokemon_id_text_view.text = resources.getString(R.string.pokemon_id, pokemon.id)
        pokemon_name_text_view.text = resources.getString(R.string.pokemon_name, pokemon.name)
        pokemon_height_text_view.text = resources.getString(R.string.pokemon_height, pokemon.height)
        pokemon_weight_text_view.text = resources.getString(R.string.pokemon_weight, pokemon.weight)
        pokemon_base_experience_text_view.text = resources.getString(R.string.pokemon_base_experience, pokemon.baseExperience)
    }

    override fun showDoneMessage() {
        Snackbar.make(activity.coordinator_layout, R.string.done, Snackbar.LENGTH_SHORT).show()
    }

    override fun showErrorMessage() {
        Snackbar.make(activity.coordinator_layout, R.string.error, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry) {
                    presenter?.getPokemonDetails(getPokemonId())
                }
                .show()
    }

}
