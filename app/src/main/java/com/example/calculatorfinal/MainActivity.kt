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
    //Represent weather the last number pressed in calculator is numeric or not
    var lastNumber: Boolean = false
    // If true the don't allow to enter another dot(.)

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

    /**
    * Append . to textView
     */

    fun onDecimalPoint(view: View) {
        // If the last appended value is numeric then append (.) or don't
        if (lastNumber && !lastDot) {
            tvInput?.append(".")
            lastNumber = false
            lastDot = true
        }
    }

    /**
     * Append +,-,*,/ operator to the TextView as the Button.text
     */
    fun onOperator(view: View){
        if (lastNumber && !isOperatorAdded(tvInput?.text.toString())){
            tvInput?.append((view as Button).text)
            lastNumber = false
            lastDot = false
        }
}

    /**
     * Calculate the output
     */
    fun onEqual(view: View){
        //if the input is number only, solution can be found
        if (lastNumber){
            // read the textView value
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                // Here if the input starts with "-" then we will separate it and perform the calculation with value
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                // If the input value contains Subtraction(-) operator
                if (tvValue.contains("-")){
                    // We will split the value with Addition(-) operator
                    var splitValue = tvValue.split("-")
                    var number1 = splitValue[0] //Value one
                    var number2 = splitValue[1] //Value two
                    if (prefix.isEmpty()){
                       number1 = prefix + number1 // If prefix is not empty then we will append it with first value that is number1
                    }
                    /** Here as value number1 and number2 with be calculated on the operator and if the result
                     * contains zero after the decimal point remove it.
                     * And display the result in TextView.
                     */
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() - number2.toDouble()}")
                }
                // If the input value contains Addition(+) operator
               else if (tvValue.contains("+")){
                    // We will split the value with Addition(+) operator
                    var splitValue = tvValue.split("+")
                    var number1 = splitValue[0] //Value one
                    var number2 = splitValue[1] //Value two
                    if (prefix.isEmpty()){
                        number1 = prefix + number1 // If prefix is not empty then we will append it with first value that is number1
                    }
                    /** Here as value number1 and number2 with be calculated on the operator and if the result
                     * contains zero after the decimal point remove it.
                     * And display the result in TextView.
                     */
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() + number2.toDouble()}")
                }
               // If the input value contains * operator
               else if (tvValue.contains("*")){
                   // We will split the value with Multiplication(*) operator
                    var splitValue = tvValue.split("*")

                    var number1 = splitValue[0]// Value one
                    var number2 = splitValue[1]// Value two
                    if (prefix.isEmpty()){
                        number1 = prefix + number1 // If prefix is not empty then we will append it with first value that is number1
                    }
                    /** Here as value number1 and number2 with be calculated on the operator and if the result
                       * contains zero after the decimal point remove it.
                        * And display the result in TextView.
                    */
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() * number2.toDouble()}")
                }
              //if input value contains division operator
              else  if (tvValue.contains("/")){
                  // We will split the value with division operator
                    var splitValue = tvValue.split("/")
                    var number1 = splitValue[0] //Value one
                    var number2 = splitValue[1] //Value two
                    if (prefix.isEmpty()){
                        number1 = prefix + number1 // If prefix is not empty then we will append it with first value that is number1
                    }
                    /* Here as value number1 and number2 with be calculated on the operator and if the result
                        contains zero after the decimal point remove it.
                        And display the result in TextView.
                     */
                    tvInput?.text = removeZeroAfterDot("${number1.toDouble() / number2.toDouble()}")
                }
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    /**
     * remove zero after decimal point
     */
    private fun removeZeroAfterDot(result : String): String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length -2)
        return  value
    }

    /**
     * It is used to check weather if any of the operator is used or not
     */

    private fun isOperatorAdded(value: String): Boolean {
        /**
         * Here first we will check that if the value starts with "-" then will ignore it.
         * As it is the result value and perform further calculation.
         */
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