package com.felipe.pokermao.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("content")
    val pokemons : List<Pokemon>
)