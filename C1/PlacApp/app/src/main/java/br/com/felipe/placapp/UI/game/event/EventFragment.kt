package br.com.felipe.placapp.UI.game.event


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import br.com.felipe.placapp.R
import br.com.felipe.placapp.UI.game.hometeam.HomeTeamFragment
import kotlinx.android.synthetic.main.fragment_event.*


class EventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bntNext.setOnClickListener {


            //val ft = activity?.supportFragmentManager?.beginTransaction()
            //ft?.replace(R.id.containerGame, HomeTeamFragment())
            //ft?.addToBackStack(null)
            //ft?.commit()

            val intent = Intent("FILTER_EVENT_NAME")
            intent.putExtra("event_name", InputEvent.text.toString())


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
        ft?.replace(R.id.containerGame, HomeTeamFragment())
        ft?.addToBackStack(null)
        ft?.commit()
    }


}
