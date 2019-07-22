package com.felipe.pokermao.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipe.pokermao.repository.PokemonRepository

class SplashViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val messageError = MutableLiveData<String>()

    fun checkHealth() {
        pokemonRepository.checkHealth(onComplete = {
            messageError.value = ""
        },
            onError ={
            messageError.value = it.message
        })
    }

}