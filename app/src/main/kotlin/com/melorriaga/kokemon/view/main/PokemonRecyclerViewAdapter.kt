package com.melorriaga.kokemon.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melorriaga.kokemon.R
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonRecyclerViewAdapter(val listener: (Int, String) -> Unit) : RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder>() {

    var pokemonNames = listOf<String>()
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
        val pokemonName = pokemonNames[position]
        holder.bind(pokemonName, listener)
    }

    override fun getItemCount() = pokemonNames.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pokemonName: String, listener: (Int, String) -> Unit) {
            itemView.pokemon_name_text_view.text = pokemonName
            itemView.setOnClickListener { listener(adapterPosition, pokemonName) }
        }

    }

}
