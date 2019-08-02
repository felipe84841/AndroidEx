package com.desiglo.jokenpo.UI.rank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.desiglo.jokenpo.R
import com.desiglo.jokenpo.model.Pontuacao
import com.desiglo.jokenpo.model.PontuacaoResponse
import kotlinx.android.synthetic.main.rank_item.view.*

class RankListAdapter (
  val pontuacao: List<Pontuacao>
) : RecyclerView.Adapter<RankListAdapter.RankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankListAdapter.RankViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rank_item, parent, false)
        return RankViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  pontuacao.size
    }
    override fun onBindViewHolder(holder: RankListAdapter.RankViewHolder, position: Int) {

    }

    class RankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pontuacao: Pontuacao
        ) =  with(itemView) {
            tvNome.text = pontuacao.nome
            tvPontos.text = pontuacao.pontuacao.toString()
        }

    }

}