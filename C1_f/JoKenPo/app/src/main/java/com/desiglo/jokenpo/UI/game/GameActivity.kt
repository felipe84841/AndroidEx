package com.desiglo.jokenpo.UI.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.desiglo.jokenpo.R
import com.desiglo.jokenpo.game.GameExec
import com.desiglo.jokenpo.model.Jokenpo
import com.desiglo.jokenpo.model.Winner
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    var gamePlayed : Boolean = false
    var points = MutableLiveData<Int>()
    val gameExec = GameExec()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        points.observe(this, Observer {
            txtPlacar.text = it.toString()
        })

        points.value = intent.getIntExtra("Points", 0)


        ivRock.setOnClickListener {
            if(!gamePlayed) playRock()
        }

        ivPaper.setOnClickListener {
            if(!gamePlayed) playPaper()
        }

        ivScisors.setOnClickListener {
            if(!gamePlayed) playScisors()
        }

        bntNewGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("Points", points.value ?: 0)
            startActivity(intent)
            finish()
        }

        bntGameOver.setOnClickListener {
            val intent = Intent(this, GameOverActivity::class.java)
            intent.putExtra("Points", points.value ?: 0)
            startActivity(intent)
            finish()
        }

        gameExec.PCmove.observe(this, Observer {
            if(it == Jokenpo.ROCK) ivPcRock.visibility = View.VISIBLE
            if(it == Jokenpo.PAPER) ivPcPaper.visibility = View.VISIBLE
            if(it == Jokenpo.SCISSORS) ivPcScisors.visibility = View.VISIBLE
        })

        gameExec.gameWinner.observe(this, Observer {
            //Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            if(it == Winner.Player ){
                bntNewGame.visibility = View.VISIBLE
                val p: Int = points.value ?: 0
                points.value = p + 3
            }
            if(it == Winner.Draw) {
                bntNewGame.visibility = View.VISIBLE
                val p: Int = points.value ?: 0
                points.value = p + 1
            }
            if(it == Winner.PC){
                bntGameOver.visibility = View.VISIBLE
                val p: Int = points.value ?: 0
                points.value = p
            }
        })
    }

    private fun playRock() {
        ivScisors.alpha = "0.3".toFloat()
        ivPaper.alpha = "0.3".toFloat()
        gameExec.playRock()
        gamePlayed = true
    }

    private fun playPaper() {
        ivRock.alpha = "0.3".toFloat()
        ivScisors.alpha = "0.3".toFloat()0000000000000000000000000000000000000000000000000000000000000000000000000000..
        gameExec.playPaper()
        gamePlayed = true
    }

    private fun playScisors() {1                                             +++++++++++++nb
        ivPaper.alpha = "0.3".toFloat()
        ivRock.alpha = "0.3".toFloat()
        gameExec.playScisors()
        gamePlayed = true
    }

}
