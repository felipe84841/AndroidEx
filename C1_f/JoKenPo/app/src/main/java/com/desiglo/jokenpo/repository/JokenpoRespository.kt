package com.desiglo.jokenpo.repository

import com.desiglo.jokenpo.model.Pontuacao
import com.desiglo.jokenpo.model.PontuacaoResponse

interface JokenpoRespository {

    fun checkHealth(
        onComplete: () -> Unit,
        onError: (t: Throwable) -> Unit
    )

    fun getPontuacao(
        onComplete: (pontuacao: List<Pontuacao>) -> Unit,
        onError: (t: Throwable) -> Unit
    )

}