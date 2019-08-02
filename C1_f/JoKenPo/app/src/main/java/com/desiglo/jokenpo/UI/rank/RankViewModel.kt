package com.desiglo.jokenpo.UI.rank

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desiglo.jokenpo.model.Pontuacao
import com.desiglo.jokenpo.repository.JokenpoRespository

class RankViewModel(
    val jokenpoRespository: JokenpoRespository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val pontuacao = MutableLiveData<List<Pontuacao>>()
    val messageResponse = MutableLiveData<String>()

    fun getPontuacao() {
        isLoading.value = true
        jokenpoRespository.getPontuacao(
            onComplete = {
                isLoading.value = false
                pontuacao.value = it
                messageResponse.value = "Dado atualizado com sucesso"
            },
            onError = {
                isLoading.value = false
                pontuacao.value = listOf()
                messageResponse.value = it.message
            }
        )
    }

}