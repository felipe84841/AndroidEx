package com.desiglo.jokenpo.api

import com.desiglo.jokenpo.model.PontuacaoResponse
import retrofit2.Call
import retrofit2.http.GET

interface JokenpoService  {

    @GET("/jokenpokemon")
    fun checkHealth(): Call<String>

    @GET("/jokenpokemon/pontuacao")
    fun getPontuacao(): Call<PontuacaoResponse>
}