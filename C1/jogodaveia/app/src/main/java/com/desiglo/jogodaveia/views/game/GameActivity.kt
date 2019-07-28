package com.desiglo.jogodaveia.views.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.desiglo.jogodaveia.R
import com.desiglo.jogodaveia.databinding.ActivityGameBinding
import com.desiglo.jogodaveia.models.Player

class GameActivity : AppCompatActivity() {

    lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_game)
        promptForPlayers()
    }

    fun onPlayersSet(player1: String, player2: String) {
        initDataBinding(player1, player2)
    }

    fun promptForPlayers() {
        val dialog = GameBeginDialog.newInstance(this)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_BEGIN_DIALOG_TAG)
    }

    private fun initDataBinding(player1: String, player2: String) {
        val activityGameBinding =
            DataBindingUtil.setContentView<ActivityGameBinding>(this, R.layout.activity_game)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel.init(player1, player2)
        activityGameBinding.gameViewModel = gameViewModel
        setUpOnGameEndListener()
    }

    private fun setUpOnGameEndListener() {
        gameViewModel.winner.observe(this, Observer { this.onGameWinnerChanged(it) })
    }

    @VisibleForTesting
    fun onGameWinnerChanged(winner: Player?) {
        val winnerName = if (winner == null || winner.name.isEmpty()) NO_WINNER else
            winner.name
        val dialog = GameEndDialog.newInstance(this, winnerName)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_END_DIALOG_TAG)
    }
    companion object {
        private val GAME_BEGIN_DIALOG_TAG = "game_dialog_tag"
        private val GAME_END_DIALOG_TAG = "game_end_dialog_tag"
        private val NO_WINNER = "No one"
    }


}
