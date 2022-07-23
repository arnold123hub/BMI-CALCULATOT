package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //here we call the cardview components that will later be initialised
        val WeightText=findViewById<EditText>(R.id.etWeight)
        val HeightText=findViewById<EditText>(R.id.etHeight)
        val calcButton=findViewById<Button>(R.id.btnCalculate)
     //after that we have an overall button below the two cardviews
        //and we initialise the view here
        calcButton.setOnClickListener {
 // here we have our textviews initialised hence they are in text formart

            val weight=WeightText.text.toString()
            val height=HeightText.text.toString()
//after that we want when values are entered there should be an expected result
            //therefore if is used to validate user input that is two values w,h wich both
            //are assighned val bmi as the value wich is weight and that it may come in decimals thats Fla
//its divided by height all equal to 100
            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //get results with decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                // we have created a fuction display results to 2 decimals wich an instance of what happens if the user does not input the details
                displayResults(bmi2Digits)
            }
        }

    }
    //here is the function
    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else->{
                return true
            }
        }
    }
// here we have the colors that would be displayed when the results or outcome is available

    private fun displayResults(bmi: Float) {
        val resultIndex=findViewById<TextView>(R.id.tvIndex)
        val resultDescription=findViewById<TextView>(R.id.tvResult)
        val info=findViewById<TextView>(R.id.tvInfo)

        resultIndex.text=bmi.toString()
        info.text="(Normal range is 18.5-24.9)"
//all these colors are predefined in the colors xml
        var resultText=""
        var color=0
        when{
            bmi<18.50->{
                resultText="Underweight"
                color=R.color.Underweight
            }
            bmi in 18.50..24.99->{
                resultText="Healthy"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText="Overweight"
                color=R.color.over_weight
            }
            bmi>29.99->{
                resultText="obese"
                color=R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text=resultText


    }
}