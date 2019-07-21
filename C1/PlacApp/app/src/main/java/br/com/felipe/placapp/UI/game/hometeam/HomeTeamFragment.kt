package br.com.felipe.placapp.UI.game.hometeam


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import br.com.felipe.placapp.R
import br.com.felipe.placapp.UI.game.awayteam.AwayTeamFragment
import kotlinx.android.synthetic.main.fragment_home_team.*
import kotlinx.android.synthetic.main.fragment_home_team.bntNext


class HomeTeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bntNext.setOnClickListener {
            //val ft = activity?.supportFragmentManager?.beginTransaction()
            //ft?.replace(R.id.containerGame, AwayTeamFragment())
            //ft?.addToBackStack(null)
            //ft?.commit()

            val intent = Intent("FILTER_HOME_TEAM_NAME")
            intent.putExtra("home_team_name", InputHomeTeam.text.toString())

            LocalBroadcastManager.
                getInstance(requireContext())
                .sendBroadcast(intent)
        }
    }

    private fun nextScreen() {
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        ft?.replace(R.id.containerGame, AwayTeamFragment())
        ft?.addToBackStack(null)
        ft?.commit()
    }

}
