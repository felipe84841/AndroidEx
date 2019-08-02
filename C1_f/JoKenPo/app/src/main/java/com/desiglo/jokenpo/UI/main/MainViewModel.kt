package com.desiglo.jokenpo.UI.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desiglo.jokenpo.repository.JokenpoRespository

class MainViewModel (
    val jokenpoRespository: JokenpoRespository
) : ViewModel() {

    val messageError = MutableLiveData<String>()

    fun checkHealth() {
        jokenpoRespository.checkHealth(onComplete = {
            messageError.value = ""
        },
            onError ={
                messageError.value = it.message
            })
    }
}