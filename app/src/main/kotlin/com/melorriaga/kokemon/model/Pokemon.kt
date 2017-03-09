package com.melorriaga.kokemon.model

data class Pokemon(
        val id: Int,
        val name: String,
        val height: Int,
        val weight: Int,
        val sprites: PokemonSprites,
        val baseExperience: Int
)
