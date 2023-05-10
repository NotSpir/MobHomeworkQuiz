package com.example.inmindcalculation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inmindcalculation.databinding.ActivityTwoBinding
import android.view.View
import android.widget.RadioButton
import androidx.core.view.get

class ActivityTwo : AppCompatActivity() {
    lateinit var binding: ActivityTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var budgetAnswerList: List<String?> = listOf(
            intent.getStringExtra("option1"),
            intent.getStringExtra("option2"),
            intent.getStringExtra("option3"),
            intent.getStringExtra("optionCorrect")
        ).shuffled()

        binding.textViewQuestion.text = intent.getStringExtra("question")
        binding.radioButton.text = budgetAnswerList.get(0)
        binding.radioButton2.text = budgetAnswerList.get(1)
        binding.radioButton3.text = budgetAnswerList.get(2)
        binding.radioButton4.text = budgetAnswerList.get(3)
    }


    fun btnClickCheckAnswer(view: View) {
        var chosenValue = "";
        if (binding.radioButton.isChecked) {
            chosenValue = binding.radioButton.text.toString()
        }
        else if (binding.radioButton2.isChecked) {
            chosenValue = binding.radioButton2.text.toString()
        }
        else if (binding.radioButton3.isChecked) {
            chosenValue = binding.radioButton3.text.toString()
        }
        else if (binding.radioButton4.isChecked) {
            chosenValue = binding.radioButton4.text.toString()
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("chosenAnswer", chosenValue)
        setResult(RESULT_OK, intent)
        finish()
    }
}