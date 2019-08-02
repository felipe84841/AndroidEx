package com.desiglo.jokenpo.game

import androidx.lifecycle.MutableLiveData
import com.desiglo.jokenpo.model.Jokenpo
import com.desiglo.jokenpo.model.Winner
import kotlin.random.Random

class GameExec {
    val gameWinner =  MutableLiveData<Winner>()
    val PCmove =  MutableLiveData<Jokenpo>()

    init {
        //gameWinner.value = "None"
    }

    private fun PcPlay() {
        val run = Random.nextInt(1,4)
        if(run == 1) PCmove.value = Jokenpo.ROCK
        if(run == 2) PCmove.value = Jokenpo.PAPER
        if(run == 3) PCmove.value = Jokenpo.SCISSORS
     }

    fun playRock() {
        PcPlay()
        Winner.Draw
        if(PCmove.value == Jokenpo.ROCK) gameWinner.value = Winner.Draw
        if(PCmove.value == Jokenpo.PAPER) gameWinner.value = Winner.PC
        if(PCmove.value == Jokenpo.SCISSORS) gameWinner.value = Winner.Player
    }

    fun playPaper() {
        PcPlay()
        if(PCmove.value == Jokenpo.ROCK) gameWinner.value = Winner.Player
        if(PCmove.value == Jokenpo.PAPER) gameWinner.value = Winner.Draw
        if(PCmove.value == Jokenpo.SCISSORS) gameWinner.value = Winner.PC
    }

    fun playScisors() {
        PcPlay()
        if(PCmove.value == Jokenpo.ROCK) gameWinner.value = Winner.PC
        if(PCmove.value == Jokenpo.PAPER) gameWinner.value = Winner.Player
        if(PCmove.value == Jokenpo.SCISSORS) gameWinner.value = Winner.Draw
    }

}