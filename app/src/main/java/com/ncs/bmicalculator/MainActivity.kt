package com.ncs.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ncs.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var currentSelectedGender = "male"
    var height:Int = 0
    var weight:Int = 0
    var age:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        height=binding.heightTv.text.toString().toInt()
        weight=binding.weightTv.text.toString().toInt()
        age=binding.ageTv.text.toString().toInt()

        binding.male.setOnClickListener{
            binding.male.setBackgroundResource(R.drawable.item_bg_selected)
            binding.female.setBackgroundResource(R.drawable.item_bg)
            currentSelectedGender="male"
        }
        binding.female.setOnClickListener{
            binding.female.setBackgroundResource(R.drawable.item_bg_selected)
            binding.male.setBackgroundResource(R.drawable.item_bg)
            currentSelectedGender="female"
        }

        val seekBar = binding.seekBar
        seekBar.max = 250
        seekBar.progress = 174
        seekBar.thumb = resources.getDrawable(R.drawable.custom_thumb)
        seekBar.progressDrawable = resources.getDrawable(R.drawable.custom_progress)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress < 50) {
                    seekBar?.progress = 50
                }
                else{
                    binding.heightTv.text=seekBar?.progress.toString()
                    height=seekBar?.progress!!
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        binding.weightPrev.setOnClickListener {
            weight--
            binding.weightTv.text=weight.toString()
        }
        binding.weightNext.setOnClickListener {
            weight++
            binding.weightTv.text=weight.toString()
        }
        binding.agePrev.setOnClickListener {
            age--
            binding.ageTv.text=age.toString()
        }
        binding.ageNext.setOnClickListener {
            age++
            binding.ageTv.text=age.toString()
        }

        binding.btnCalculateBMI.setOnClickListener {
            val bmi=calculateBMI(height.toDouble(),weight.toDouble(),age,currentSelectedGender)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }


    }

    fun calculateBMI(heightCm: Double, weightKg: Double, age: Int, gender: String): Double {
        Log.d("checkheight",heightCm.toString())
        Log.d("checkweight",weightKg.toString())
        Log.d("checkage",age.toString())
        Log.d("checgender",gender.toString())

        val heightM = heightCm / 100
        val bmi = weightKg / (heightM * heightM)
        val isMale= gender=="male"
        val adjustedBMI: Double = when {
            isMale && age < 18 -> bmi - 1
            !isMale && age < 18 -> bmi + 1
            else -> bmi
        }

        return adjustedBMI
    }
}