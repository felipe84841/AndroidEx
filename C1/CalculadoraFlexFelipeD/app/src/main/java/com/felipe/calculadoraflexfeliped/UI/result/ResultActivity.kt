package com.felipe.calculadoraflexfeliped.UI.result

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.felipe.calculadoraflexfeliped.Model.CarData
import com.felipe.calculadoraflexfeliped.R
import com.felipe.calculadoraflexfeliped.extensions.format
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if (intent.extras == null) {
            Toast.makeText(this, "Não foi possível realizar a operação",
                Toast.LENGTH_SHORT).show()
        } else {
            calculate()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun calculate() {

        val carData = intent.extras?.getParcelable<CarData>("CAR_DATA")

        carData?.run{
            val performanceOfMyCar = this.ethanolAverage / this.gasAverage
            val priceOfFuelIndice = this.ethanolPrice / this.gasPrice

            if (priceOfFuelIndice <= performanceOfMyCar) {
                tvSuggestion.text = getString(R.string.ethanol)
            } else {
                tvSuggestion.text = getString(R.string.gasoline)
            }

            tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage).format(2)
            tvGasAverageResult.text = (gasPrice / gasAverage).format(2)

            tvFuelRatio.text =
                getString(R.string.label_fuel_ratio,performanceOfMyCar.format(2))

        }

//        val gasPrice = intent.extras?.getDouble("GAS_PRICE", 0.0) ?: 0.0
//        val ethanolPrice = intent.extras?.getDouble("ETHANOL_PRICE", 0.0) ?: 0.0
//        val gasAverage = intent.extras?.getDouble("GAS_AVERAGE", 0.0) ?: 0.0
//        val ethanolAverage = intent.extras?.getDouble("ETHANOL_AVERAGE", 0.0) ?: 0.0
//
//        val performanceOfMyCar = ethanolAverage / gasAverage
//        val priceOfFuelIndice = ethanolPrice / gasPrice

        //val gasPrice = carData?.gasPrice


//        if (priceOfFuelIndice <= performanceOfMyCar) {
//            tvSuggestion.text = getString(R.string.ethanol)
//        } else {
//            tvSuggestion.text = getString(R.string.gasoline)
//        }
        //tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage).toString()
        //tvGasAverageResult.text = (gasPrice / gasAverage).toString()

//        tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage).format(2)
//        tvGasAverageResult.text = (gasPrice / gasAverage).format(2)
//
//        tvFuelRatio.text =
//            getString(R.string.label_fuel_ratio,performanceOfMyCar.format(2))
    }

}
