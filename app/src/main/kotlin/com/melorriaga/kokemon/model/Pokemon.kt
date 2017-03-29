package com.melorriaga.kokemon.model

data class Pokemon(
        val id: Int,
        val name: String,
        val height: Int = 0,
        val weight: Int = 0,
        val sprites: PokemonSprites = PokemonSprites(),
        val baseExperience: Int = 0
)
