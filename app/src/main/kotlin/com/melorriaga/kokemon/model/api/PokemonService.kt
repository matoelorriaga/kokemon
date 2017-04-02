package com.melorriaga.kokemon.model.api

import com.melorriaga.kokemon.model.APIResourceList
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.model.Type
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single

interface PokemonService {

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int): Single<APIResourceList>

    @GET("pokemon/{id}")
    fun getPokemonDetails(@Path("id") id: Int): Single<Pokemon>

    @GET("type")
    fun getPokemonTypes(): Single<APIResourceList>

    @GET("type/{id}")
    fun getPokemonType(@Path("id") id: Int): Single<Type>

}
