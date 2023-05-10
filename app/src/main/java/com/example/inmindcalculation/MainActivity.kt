package com.example.inmindcalculation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.inmindcalculation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val qList: List<Questions> = listOf(
        Questions("Q1", setOf<String>("Верно", "", "", "")),
        Questions("Q2", setOf<String>("Верно", "", "", "")),
        Questions("Q3", setOf<String>("Верно", "", "", "")),
        Questions("Q4", setOf<String>("Верно", "", "", "")),
        Questions("Q5", setOf<String>("Верно", "", "", "")),
        Questions("Q6", setOf<String>("Верно", "", "", "")),
        Questions("Q7", setOf<String>("Верно", "", "", "")),
        Questions("Q8", setOf<String>("Верно", "", "", "")),
        Questions("Q9", setOf<String>("Верно", "", "", "")),
        Questions("Q10", setOf<String>("Верно", "", "", "")),
        Questions("Q11", setOf<String>("Верно", "", "", "")),
        Questions("Q12", setOf<String>("Верно", "", "", "")),
        Questions("Q13", setOf<String>("Верно", "", "", "")),
        Questions("Q14", setOf<String>("Верно", "", "", "")),
        Questions("Q15", setOf<String>("Верно", "", "", "")),
        Questions("Q16", setOf<String>("Верно", "", "", "")),
        Questions("Q17", setOf<String>("Верно", "", "", "")),
        Questions("Q18", setOf<String>("Верно", "", "", "")),
        Questions("Q19", setOf<String>("Верно", "", "", "")),
        Questions("Q20", setOf<String>("Верно", "", "", "")),
        Questions("Q21", setOf<String>("Верно", "", "", "")),
        Questions("Q22", setOf<String>("Верно", "", "", "")),
        Questions("Q23", setOf<String>("Верно", "", "", "")),
        Questions("Q24", setOf<String>("Верно", "", "", "")),
        Questions("Q25", setOf<String>("Верно", "", "", "")),
        Questions("Q26", setOf<String>("Верно", "", "", "")),
        Questions("Q27", setOf<String>("Верно", "", "", "")),
        Questions("Q28", setOf<String>("Верно", "", "", "")),
        Questions("Q29", setOf<String>("Верно", "", "", "")),
        Questions("Q30", setOf<String>("Верно", "", "", ""))
    )

    var qGameList = (qList.shuffled()).take(10)
    var gameInProgress = false
    var qTotal = 0;
    var qCorrect = 0;
    var correctA = 0
    var questionAnswered = 0
    var chosenAnswer = ""

    lateinit var binding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val answer = result.data?.getStringExtra("chosenAnswer")
                    var corr = "Неправильный ответ"
                    if (answer == qGameList.get(questionAnswered).answers.elementAtOrNull(0).toString()) {
                        corr = "Правильный ответ"
                        qCorrect++
                    }
                    val qText = qGameList.get(questionAnswered).questionText
                    questionAnswered++
                    if (questionAnswered == 10)
                    {
                        binding.buttonStats.isEnabled = true
                        gameInProgress = false
                        qTotal += questionAnswered
                        questionAnswered = 0
                        binding.buttonQuestion.text = "Начать"
                    }
                    binding.buttonQuestion.isEnabled = true
                    binding.buttonAnswer.isEnabled = false
                    binding.textQuest.text =
                        qText + "\n Выбранный ответ: " + answer + "\n" + corr
                }
            }
    }



    fun btnClickNext(view: View) {
        if (gameInProgress) {
                binding.textQuest.text = qGameList.get(questionAnswered).questionText
                binding.textView.text = "Вопрос " + (questionAnswered+1)
                binding.buttonAnswer.isEnabled = true
                binding.buttonQuestion.isEnabled = false
                binding.buttonQuestion.text = "Загадка"
        }
        else {
            qGameList = (qList.shuffled()).take(10)
            binding.textQuest.text = qGameList.get(questionAnswered).questionText
            binding.buttonAnswer.isEnabled = true
            binding.buttonStats.isEnabled = false
            gameInProgress = true
            binding.textView.text = "Вопрос 1"
            questionAnswered = 0
            qCorrect = 0
        }
    }

    fun btnClickAnswer(view: View) {
        val intent = Intent(this, ActivityTwo::class.java)
        intent.putExtra("question", qGameList.get(questionAnswered).questionText)
        intent.putExtra("optionCorrect", qGameList.get(questionAnswered).answers.elementAtOrNull(0))
        intent.putExtra("option2", qGameList.get(questionAnswered).answers.elementAtOrNull(1))
        intent.putExtra("option3", qGameList.get(questionAnswered).answers.elementAtOrNull(2))
        intent.putExtra("option3", qGameList.get(questionAnswered).answers.elementAtOrNull(3))
        launcher?.launch(intent)
    }

    fun btnClickStats(view: View) {
        val intent2 = Intent(this, ActivityStats::class.java)
        intent2.putExtra("qTotal", qTotal.toString())
        intent2.putExtra("qCorrect", qCorrect.toString())
        intent2.putExtra("qWrong", (qTotal - qCorrect).toString())
        startActivity(intent2)
    }
}

//to make things easier, the first value in answers is the correct answer
class Questions(
    val questionText: String,
    var answers:Set<String>) {
}

