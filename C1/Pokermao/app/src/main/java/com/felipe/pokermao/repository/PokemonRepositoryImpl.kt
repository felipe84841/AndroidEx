package com.felipe.pokermao.repository

import com.felipe.pokermao.api.PokemonService
import com.felipe.pokermao.model.HealthResponse
import com.felipe.pokermao.model.Pokemon
import com.felipe.pokermao.model.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(
    val pokemonService: PokemonService
) : PokemonRepository {

    override fun updatePokemon(pokemon: Pokemon,
                            onComplete: (Pokemon) -> Unit,
                            onError: (t: Throwable) -> Unit) {
        pokemonService.updatePokemon(pokemon)
            .enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(response.isSuccessful()) {
                        onComplete(response.body()!!)
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }

            })
    }

    override fun getPokemons(
        sort: String,
        size: Int,
        onComplete: (pokemons: List<Pokemon>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        pokemonService.getPokemon(sort, size)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                   onError(t)
                }

                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                   if(response.isSuccessful()) {
                       onComplete(response.body()?.pokemons ?: listOf())
                   } else {
                       onError(Throwable("Não foi possível realizar a requisição"))
                   }
                }

            })
    }

    override fun checkHealth(
        onComplete: () -> Unit,
        onError: (t: Throwable) -> Unit) {
        pokemonService.checkHealth()
            .enqueue(object: Callback<HealthResponse> {

                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<HealthResponse>, response: Response<HealthResponse>) {
                    if(response.isSuccessful) {
                        onComplete()
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }

}