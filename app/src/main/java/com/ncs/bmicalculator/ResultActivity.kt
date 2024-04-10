package com.ncs.bmicalculator

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ncs.bmicalculator.databinding.ActivityMainBinding
import com.ncs.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    val binding: ActivityResultBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bmi = intent.getDoubleExtra("bmi", 0.0)
        if (bmi<=18.5){
            binding.result.text="Underweight"
            binding.result.setTextColor(resources.getColor(R.color.red))
            val formattedBMI = String.format("%.1f", bmi)
            binding.bmiNum.text=formattedBMI
            binding.quote.text="Your body deserves love and care. Listen to what it needs."
        }
        else if (bmi>18.5 && bmi<=24.9){
            binding.result.text="Normal"
            binding.result.setTextColor(resources.getColor(R.color.green))
            val formattedBMI = String.format("%.1f", bmi)
            binding.bmiNum.text=formattedBMI
            binding.quote.text="You have a normal body weight. Good Job."

        }
        else if (bmi>24.9 && bmi<=29.9){
            binding.result.text="OverWeight"
            binding.result.setTextColor(resources.getColor(R.color.yellow))
            val formattedBMI = String.format("%.1f", bmi)
            binding.bmiNum.text=formattedBMI
            binding.quote.text="Every journey begins with a single step. Start yours towards a healthier lifestyle today."

        }
        else if (bmi>29.9){
            binding.result.text="Obese"
            binding.result.setTextColor(resources.getColor(R.color.red))
            val formattedBMI = String.format("%.1f", bmi)
            binding.bmiNum.text=formattedBMI
            binding.quote.text="Every small step towards a healthier you is a victory. Keep moving forward."

        }

        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.btnCalculateBMI.setOnClickListener {
            onBackPressed()
        }

    }
}