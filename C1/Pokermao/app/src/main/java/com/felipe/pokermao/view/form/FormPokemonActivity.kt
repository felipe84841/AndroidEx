package com.felipe.pokermao.view.form

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.felipe.pokermao.R
import com.felipe.pokermao.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_pokemon.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FormPokemonActivity : AppCompatActivity() {


    val formViewModel : FormPokemonViewModel by viewModel()
    val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pokemon)

        val pokemon = intent.getParcelableExtra<Pokemon>("POKEMON")

        tvPokemonNameForm.text = pokemon.name

        sbPS.progress = pokemon.ps
        sbAttack.progress = pokemon.attack
        sbDefense.progress = pokemon.defense
        sbVelocity.progress = pokemon.velocity

        tvPSValue.text = pokemon.ps.toString()
        tvAttackValue.text = pokemon.attack.toString()
        tvDefenseValue.text = pokemon.defense.toString()
        tvVelocityValue.text = pokemon.velocity.toString()

        picasso.load("https://pokedexdx.herokuapp.com${pokemon.imageURL}").into(ivPokemonForm)

        setListener(sbAttack, tvAttackValue)
        setListener(sbDefense, tvDefenseValue)
        setListener(sbVelocity, tvVelocityValue)
        setListener(sbPS, tvPSValue)

        formViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        btSaveForm.setOnClickListener {
            pokemon.attack = sbAttack.progress
            pokemon.defense = sbDefense.progress
            pokemon.velocity = sbVelocity.progress
            pokemon.ps = sbPS.progress

            formViewModel.updatePokemon(pokemon)
        }

    }

    private fun setListener(seekBar: SeekBar, textView: TextView) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
