package br.com.felipe.placapp.UI.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.felipe.placapp.R
import br.com.felipe.placapp.UI.game.awayteam.AwayTeamFragment
import br.com.felipe.placapp.UI.game.event.EventFragment
import br.com.felipe.placapp.UI.game.hometeam.HomeTeamFragment
import br.com.felipe.placapp.UI.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        ivBack.setOnClickListener {
            onBackPressed()
        }

        if(savedInstanceState == null)
            showEventFragment()

        registerBroadcastReceiver()
    }

    private fun showEventFragment() {
        val ft = supportFragmentManager.beginTransaction()
        if(supportFragmentManager.findFragmentByTag("EventFragment") == null){
            ft.add(R.id.containerGame,EventFragment())
            ft.commit()
        }
    }

    private fun registerBroadcastReceiver() {
        val intentFilter = IntentFilter("FILTER_EVENT_NAME")
        intentFilter.addAction("FILTER_HOME_TEAM_NAME")
        intentFilter.addAction("FILTER_AWAY_TEAM_NAME")

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(myReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(myReceiver)
    }

    private val myReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent) {
           if(intent.hasExtra("event_name")){
               gameViewModel.eventName = intent.getStringExtra("event_name")
               nextFragment(HomeTeamFragment())
           }
            if(intent.hasExtra("home_team_name")){
                gameViewModel.homeTeam = intent.getStringExtra("home_team_name")
                nextFragment(AwayTeamFragment())
            }
            if(intent.hasExtra("away_team_name")){
                gameViewModel.awayTeam = intent.getStringExtra("away_team_name")
                callScore()
            }
        }

        private fun nextFragment(fragment: Fragment) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.containerGame, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    private fun callScore() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("event_name", gameViewModel.eventName)
        intent.putExtra("home_team_name", gameViewModel.homeTeam)
        intent.putExtra("away_team_name", gameViewModel.awayTeam)
        startActivity(intent)
        this.finish()
    }
}
