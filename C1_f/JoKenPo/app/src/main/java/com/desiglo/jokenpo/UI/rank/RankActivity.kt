package com.desiglo.jokenpo.UI.rank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.desiglo.jokenpo.R
import kotlinx.android.synthetic.main.activity_rank.*
import org.koin.android.viewmodel.ext.android.viewModel

class RankActivity : AppCompatActivity() {

    val rankViewModel: RankViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)


        rankViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                //containerLoading.visibility = View.VISIBLE
            } else {
                //containerLoading.visibility = View.GONE
            }
        })

        rankViewModel.messageResponse.observe(this, Observer {
            if(it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        rankViewModel.pontuacao.observe(this, Observer {
            rvRank.adapter = RankListAdapter(it)
            //rvRank.layoutManager = GridLayoutManager(this, 3)
        })

        rankViewModel.getPontuacao()
    }
}
