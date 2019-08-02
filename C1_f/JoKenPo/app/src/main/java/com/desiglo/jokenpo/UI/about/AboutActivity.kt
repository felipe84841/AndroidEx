package com.desiglo.jokenpo.UI.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.desiglo.jokenpo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about.*
import org.koin.android.ext.android.inject

class AboutActivity : AppCompatActivity() {

    val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

       picasso.load("https://publicdomainvectors.org/photos/rock-paper-scissors.png").into(ivLogo)

        bntBack.setOnClickListener {
            finish()
        }

    }
}
