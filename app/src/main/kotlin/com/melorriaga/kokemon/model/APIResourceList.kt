package com.melorriaga.kokemon.model

data class APIResourceList(
        val count: Int,
        val next: String,
        val previous: Boolean,
        val results: List<NamedAPIResource>
)
