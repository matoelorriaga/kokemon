package com.melorriaga.kokemon.model

data class Type(
        val id: Int,
        val name: String,
        val pokemon: List<TypePokemon> = listOf()
)
