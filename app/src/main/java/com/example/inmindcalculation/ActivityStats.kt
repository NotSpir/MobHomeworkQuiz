package com.example.inmindcalculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.inmindcalculation.databinding.ActivityStatsBinding

class ActivityStats : AppCompatActivity() {

    lateinit var binding: ActivityStatsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTotal.text = "Всего ответов: " + intent.getStringExtra("qTotal")
        binding.tvCorrect.text = "Правильных ответов: " + intent.getStringExtra("qCorrect")
        binding.tvWrong.text = "Неправильных ответов: " + intent.getStringExtra("qWrong")
    }

    fun btnBack(view: View) {
        finish()
    }
}