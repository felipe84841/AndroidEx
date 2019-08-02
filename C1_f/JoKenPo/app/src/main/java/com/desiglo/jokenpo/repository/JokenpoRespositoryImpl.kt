package com.desiglo.jokenpo.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.desiglo.jokenpo.api.JokenpoService
import com.desiglo.jokenpo.model.Pontuacao
import com.desiglo.jokenpo.model.PontuacaoResponse

class JokenpoRespositoryImpl(
    val jokenpoService: JokenpoService
) : JokenpoRespository {

    override fun getPontuacao(
        onComplete: (pontuacao: List<Pontuacao>) -> Unit,
        onError: (t: Throwable) -> Unit) {
        jokenpoService.getPontuacao()
            .enqueue(object:  Callback<PontuacaoResponse>{
                override fun onFailure(call: Call<PontuacaoResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<PontuacaoResponse>, response: Response<PontuacaoResponse>) {
                    if(response.isSuccessful()) {
                        onComplete(response.body()?.pontuacoes ?: listOf())
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            }
        )
    }

    override fun checkHealth(
        onComplete: () -> Unit,
        onError: (t: Throwable) -> Unit) {
        jokenpoService.checkHealth()
            .enqueue(object: Callback<String> {

                override fun onFailure(call: Call<String>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful) {
                        onComplete()
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }

}