package com.example.bmicalculator

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BMIActivity : AppCompatActivity() {

    private lateinit var bmiDisplay: TextView
    private lateinit var bmiCategoryDisplay: TextView
    private lateinit var genderDisplay: TextView
    private lateinit var goToMain: Button
    private lateinit var imageView: ImageView
    private lateinit var background: RelativeLayout

    private lateinit var intent: Intent

    private var bmi: String? = null
    private var height: String? = null
    private var weight: String? = null

    private var intBmi: Float = 0.0f
    private var intheight: Float = 0.0f
    private var intWeight: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        supportActionBar?.elevation = 0f
        val colorDrawable = ColorDrawable(Color.parseColor("#1E1D1D"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        supportActionBar?.title = Html.fromHtml("<font color=\"white\"></font>")
        supportActionBar?.title = "Result"

        intent = getIntent()

        bmiDisplay = findViewById(R.id.bmidisplay)
        bmiCategoryDisplay = findViewById(R.id.bmicategorydispaly)
        genderDisplay = findViewById(R.id.genderdisplay)
        goToMain = findViewById(R.id.gotomain)
        imageView = findViewById(R.id.imageview)
        background = findViewById(R.id.contentlayout)

        height = intent.getStringExtra("height")
        weight = intent.getStringExtra("weight")

        intheight = height?.toFloatOrNull() ?: 0.0f
        intWeight = weight?.toFloatOrNull() ?: 0.0f

        intheight /= 100
        intBmi = intWeight / (intheight * intheight)

        bmi = intBmi.toString()

        if (intBmi < 16) {
            bmiCategoryDisplay.text = "Severe Thinness"
        } else if (intBmi < 16.9 && intBmi > 16) {
            bmiCategoryDisplay.text = "Moderate Thinness"

        } else if (intBmi < 18.4 && intBmi > 17) {
            bmiCategoryDisplay.text = "Mild Thinness"

        } else if (intBmi < 24.9 && intBmi > 18.5) {
            bmiCategoryDisplay.text = "Normal"
        } else if (intBmi < 29.9 && intBmi > 25) {
            bmiCategoryDisplay.text = "Overweight"

        } else if (intBmi < 34.9 && intBmi > 30) {
            bmiCategoryDisplay.text = "Obese Class I"

        } else {
            bmiCategoryDisplay.text = "Obese Class II"

        }

        genderDisplay.text = intent.getStringExtra("gender")
        bmiDisplay.text = bmi

        goToMain.setOnClickListener {
            val intent1 = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent1)
        }
    }
}
