package br.com.felipe.placapp.UI.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.felipe.placapp.R
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    private lateinit var  scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreViewModel = ViewModelProviders
            .of(this).get(ScoreViewModel::class.java)

        tvEventName.text = intent.getStringExtra("event_name")
        tvHome.text = intent.getStringExtra("home_team_name")
        tvGoHome.text = intent.getStringExtra("away_team_name")

        btGoHome.setOnClickListener {
            scoreViewModel.MarkGoalHome()
        }

        btGoAway.setOnClickListener {
            scoreViewModel.MarkGoalAway()
        }

        btRestart.setOnClickListener {
            scoreViewModel.restart()
        }

        btfinish.setOnClickListener {
            finish()
        }

        scoreViewModel.goalHome.observe(this, Observer {
            tvHomeValue.text = it.toString()
        })

        scoreViewModel.goalAway.observe(this, Observer {
            tvAwayValue.text = it.toString()
        })
    }
}
