package com.eliott.training.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eliott.training.sevenminutesworkout.databinding.ActivityBmiactivityBinding
import java.lang.RuntimeException
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private var binding: ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBMI)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }

        binding?.toolbarBMI?.setNavigationOnClickListener() {
            onBackPressed()
        }

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUsUnitsView()
            }

        }

        binding?.btnCalculate?.setOnClickListener {

            var bmi: Float;
            if (binding?.rbMetricUnits!!.isChecked) {
                if (validateMetricUnits()) {
                    bmi = getBMIMetricsUnit()
                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(
                        this,
                        "Please enter right value",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                if (validateUSUnits()) {

                    bmi = getBMIUsUnit()
                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(
                        this,
                        "Please enter right value",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }


    }

    private fun validateUSUnits(): Boolean {
        return !(binding?.etUsMetricUnitWeight?.text.toString()
            .isEmpty() || binding?.etMetricUnitFeet?.text.toString().isEmpty() ||
                binding?.etMetricUnitInch?.text.toString().isEmpty())
    }

    private fun makeVisibleUsUnitsView() {
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE
        binding?.llDisplayUsMetrics?.visibility = View.VISIBLE

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.etUsMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitFeet?.text!!.clear()
        binding?.etMetricUnitInch?.text!!.clear()

    }

    private fun makeVisibleMetricUnitsView() {
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.GONE
        binding?.llDisplayUsMetrics?.visibility = View.GONE

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE



        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

    }

    private fun getBMIUsUnit(): Float {
        val weightValue: Float = binding?.etUsMetricUnitWeight?.text.toString().toFloat()
        val inchValue: Float = binding?.etMetricUnitInch?.text.toString().toFloat()
        val feetValue: Float = binding?.etMetricUnitFeet?.text.toString().toFloat()

        val heightValue = inchValue + feetValue * 12

        val bmi = 703 * (weightValue / (heightValue * heightValue))
        return bmi
    }

    private fun getBMIMetricsUnit(): Float {
        val heightValue: Float =
            binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
        val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

        val bmi = weightValue / (heightValue * heightValue)
        return bmi
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }


        binding?.llDisplayBMIResult?.visibility = View.VISIBLE


        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()


        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription

    }

    private fun validateMetricUnits(): Boolean {
        return !(binding?.etMetricUnitWeight?.text.toString()
            .isEmpty() || binding?.etMetricUnitHeight?.text.toString().isEmpty())
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}