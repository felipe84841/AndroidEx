package com.desiglo.jokenpo.UI.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.desiglo.jokenpo.R
import com.desiglo.jokenpo.UI.about.AboutActivity
import com.desiglo.jokenpo.UI.game.GameActivity
import com.desiglo.jokenpo.UI.rank.RankActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bntPlay.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        bntRank.setOnClickListener {
            startActivity(Intent(this, RankActivity::class.java))
        }

        bntAbout.setOnClickListener {
            startActivity(Intent( this, AboutActivity::class.java))
        }

        bntClose.setOnClickListener {
            finish()
        }

        mainViewModel.checkHealth()

        mainViewModel.messageError.observe(this, Observer {
            if(it == "") {
                //startActivity(Intent(this, MainActivity::class.java))
                bntRank.isEnabled  = true
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                bntRank.isEnabled  = false
            }
        })


    }
}
