package com.felipe.pokermao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipe.pokermao.model.Pokemon
import com.felipe.pokermao.repository.PokemonRepository

class ListPokemonViewModel(
    val pokemonRepository: PokemonRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val pokemons = MutableLiveData<List<Pokemon>>()
    val messageResponse = MutableLiveData<String>()

    fun getPokemons() {
        isLoading.value = true
        pokemonRepository.getPokemons(
            sort = "number,asc",
            size  = 150,
            onComplete = {
                isLoading.value = false
                pokemons.value = it
                messageResponse.value = "Dado atualizado com sucesso"
            },
            onError = {
                isLoading.value = false
                pokemons.value = listOf()
                messageResponse.value = it.message
            }
        )
    }

}