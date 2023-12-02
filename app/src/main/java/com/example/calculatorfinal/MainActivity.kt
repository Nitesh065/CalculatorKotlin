package com.example.calculatorfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var tvInput: TextView? = null
    var lastNumber: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.textView)

    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumber = true
        lastDot = false
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastNumber && !lastDot) {
            tvInput?.append(".")
            lastNumber = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        if (lastNumber && !isOperatorAdded(tvInput?.text.toString())){
            tvInput?.append((view as Button).text)
            lastNumber = false
            lastDot = false
        }
}
    fun onEqual(view: View){
        if (lastNumber){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    var splitValue = tvValue.split("-")
                    var number1 = splitValue[0]
                    var number2 = splitValue[1]
                    if (prefix.isEmpty()){
                       number1 = prefix + number1
                    }
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() - number2.toDouble()}")
                }
               else if (tvValue.contains("+")){
                    var splitValue = tvValue.split("+")
                    var number1 = splitValue[0]
                    var number2 = splitValue[1]
                    if (prefix.isEmpty()){
                        number1 = prefix + number1
                    }
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() + number2.toDouble()}")
                }
               else if (tvValue.contains("*")){
                    var splitValue = tvValue.split("*")
                    var number1 = splitValue[0]
                    var number2 = splitValue[1]
                    if (prefix.isEmpty()){
                        number1 = prefix + number1
                    }
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() * number2.toDouble()}")
                }
              else  if (tvValue.contains("/")){
                    var splitValue = tvValue.split("/")
                    var number1 = splitValue[0]
                    var number2 = splitValue[1]
                    if (prefix.isEmpty()){
                        number1 = prefix + number1
                    }
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() / number2.toDouble()}")
                }
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String): String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length -2)
        return  value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}