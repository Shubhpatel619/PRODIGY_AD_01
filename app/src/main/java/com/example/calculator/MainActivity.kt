package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var input = ""
    private var operator = ""
    private var value1 = 0.0
    private var value2 = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "onCreate: Activity created, view binding initialized")


        setNumberClickListener()

        binding.btnClear.setOnClickListener {
            clearInput()
        }

        binding.btnDelete.setOnClickListener {
            deleteLastCharacter()
        }

        binding.btnAdd.setOnClickListener {
            setOperator("+")
        }

        binding.btnSubtract.setOnClickListener {
            setOperator("-")
        }

        binding.btnMultiply.setOnClickListener {
            setOperator("*")
        }

        binding.btnDivide.setOnClickListener {
            setOperator("/")
        }

        binding.btnEquals.setOnClickListener {
            calculateResult()
        }

    }

    private fun setNumberClickListener() {
        val numberButtons = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3,
            binding.btn4, binding.btn5, binding.btn6, binding.btn7,
            binding.btn8, binding.btn9, binding.btnDecimal
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                val value = (it as Button).text.toString()
                input += value
                binding.tvDisplay.text = input
                Log.d("MainActivity", "Number clicked: $value, input: $input")
            }
        }
    }

    private fun clearInput() {
        input = ""
        operator = ""
        value1 = 0.0
        value2 = 0.0
        binding.tvDisplay.text = "0"
        Log.d("MainActivity", "clearInput: Input cleared")
    }

    private fun deleteLastCharacter() {
        if (input.isNotEmpty()) {
            input = input.substring(0, input.length - 1)
            binding.tvDisplay.text = if (input.isEmpty()) "0" else input
            Log.d("MainActivity", "deleteLastCharacter: Input after deletion: $input")
        }
    }

    private fun setOperator(op: String) {
        if (input.isNotEmpty()) {
            value1 = input.toDouble()
            operator = op
            input = ""
            Log.d("MainActivity", "setOperator: Operator set to $operator, value1: $value1")
        }
    }

    private fun calculateResult() {
        if (input.isNotEmpty()) {
            value2 = input.toDouble()
            val result = when (operator) {
                "+" -> value1 + value2
                "-" -> value1 - value2
                "*" -> value1 * value2
                "/" -> value1 / value2
                else -> 0.0
            }
            binding.tvDisplay.text = result.toString()
            input = result.toString()
            Log.d("MainActivity", "calculateResult: Result: $result")
        }
    }
}
