package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var currentHeight: TextView
    private lateinit var currentWeight: TextView
    private lateinit var currentAge: TextView
    private lateinit var incrementAge: ImageView
    private lateinit var decrementAge: ImageView
    private lateinit var incrementWeight: ImageView
    private lateinit var decrementWeight: ImageView
    private lateinit var calculateBMI: Button
    private lateinit var seekBarForHeight: SeekBar
    private lateinit var male: RelativeLayout
    private lateinit var female: RelativeLayout

    private var weight = 55
    private var age = 22
    private var currentProgress: Int = 170
    private var progressString = "170"
    private var userType = "0"
    private var weightString = "55"
    private var ageString = "22"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        currentAge = findViewById(R.id.currentage)
        currentWeight = findViewById(R.id.currentweight)
        currentHeight = findViewById(R.id.currentheight)
        incrementAge = findViewById(R.id.incrementage)
        decrementAge = findViewById(R.id.decrementage)
        incrementWeight = findViewById(R.id.incremetweight)
        decrementWeight = findViewById(R.id.decrementweight)
        calculateBMI = findViewById(R.id.calculatebmi)
        seekBarForHeight = findViewById(R.id.seekbarforheight)
        male = findViewById(R.id.male)
        female = findViewById(R.id.female)

        male.setOnClickListener {
            male.background = ContextCompat.getDrawable(applicationContext, R.drawable.male)
            female.background = ContextCompat.getDrawable(applicationContext, R.drawable.female)
            userType = "Male"
        }

        female.setOnClickListener {
            female.background = ContextCompat.getDrawable(applicationContext, R.drawable.female)
            male.background = ContextCompat.getDrawable(applicationContext, R.drawable.female)
            userType = "Female"
        }

        seekBarForHeight.max = 300
        seekBarForHeight.progress = 170
        seekBarForHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentProgress = progress
                progressString = currentProgress.toString()
                currentHeight.text = progressString
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        incrementWeight.setOnClickListener {
            weight++
            weightString = weight.toString()
            currentWeight.text = weightString
        }

        incrementAge.setOnClickListener {
            age++
            ageString = age.toString()
            currentAge.text = ageString
        }

        decrementAge.setOnClickListener {
            age--
            ageString = age.toString()
            currentAge.text = ageString
        }

        decrementWeight.setOnClickListener {
            weight--
            weightString = weight.toString()
            currentWeight.text = weightString
        }

        calculateBMI.setOnClickListener {
            if (userType == "0") {
                Toast.makeText(applicationContext, "Select Your Gender First", Toast.LENGTH_SHORT).show()
            } else if (progressString == "0") {
                Toast.makeText(applicationContext, "Select Your Height First", Toast.LENGTH_SHORT).show()
            } else if (age == 0 || age < 0) {
                Toast.makeText(applicationContext, "Age is Incorrect", Toast.LENGTH_SHORT).show()
            } else if (weight == 0 || weight < 0) {
                Toast.makeText(applicationContext, "Weight Is Incorrect", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, BMIActivity::class.java)
                intent.putExtra("gender", userType)
                intent.putExtra("height", progressString)
                intent.putExtra("weight", weightString)
                intent.putExtra("age", ageString)
                startActivity(intent)

            }
        }
    }
}
