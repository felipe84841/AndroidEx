package com.felipe.pokermao.api

import com.felipe.pokermao.model.HealthResponse
import com.felipe.pokermao.model.Pokemon
import com.felipe.pokermao.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {

    @GET(value = "/api/pokemon/health")
    fun checkHealth(): Call<HealthResponse>

    @GET("/api/pokemon/")
    fun getPokemon(
        @Query("sort") sort: String,
        @Query("size") size: Int
    ): Call<PokemonResponse>

    @PUT("/api/pokemon/")
    fun updatePokemon(
        @Body pokemon: Pokemon
    ): Call<Pokemon>

    @GET("/api/pokemon/{number}")
    fun getPokemon(
        @Path("number")
        number: String
    ) : Call<Pokemon>


}