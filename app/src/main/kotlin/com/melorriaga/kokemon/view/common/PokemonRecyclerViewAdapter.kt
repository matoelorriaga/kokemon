package com.melorriaga.kokemon.view.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonRecyclerViewAdapter(val listener: (Pokemon) -> Unit) : RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder>() {

    var pokemonList = listOf<Pokemon>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon, listener)
    }

    override fun getItemCount() = pokemonList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pokemon: Pokemon, listener: (Pokemon) -> Unit) {
            itemView.pokemon_name_text_view.text = pokemon.name
            itemView.setOnClickListener { listener(pokemon) }
        }

    }

}
