package com.example.citizen_care

import android.os.Bundle
import android.provider.MediaStore
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.util.concurrent.TimeUnit


class BasicQuiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //lateinit answers
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_quiz)

        val questionText = findViewById<TextView>(R.id.curQuestion)
        val op1 = findViewById<RadioButton>(R.id.op1)
        val op2 = findViewById<RadioButton>(R.id.op2)
        val options = arrayOf("a","b","c","d")
        newQuestion("ti",1,questionText, op1, op2, options)
        newQuestion("it",2,questionText, op1, op2, options)
        /*
        val test = Quiz()
        test.fillQuiz()
        var a = 0
        test.getQuestions().forEach {
            newQuestion(it,a++,questionText)
        }
         */
    }
    //just send the quiz instead
    private fun newQuestion(question: String, number: Int, questionText: TextView, op1: RadioButton, op2: RadioButton, options: Array<String>) : Boolean {
        var right: Boolean = false
        var clicked: Boolean = false
        questionText.text = "$number - $question"
        op1.text = options[(number * 2) - 2]
        op2.text = options[(number * 2) - 1]
        /*
        do while?

        op1.setOnClickListener{
            if (quiz.corrects[number].equals(op1.text))
                right = true
            clicked = true
        }
         */
            op1.setOnClickListener {
                right = true
                clicked = true
            }

            op2.setOnClickListener {
                right = true
                clicked = true
            }

        return right
    }
}

class Quiz() {
    private val questions : MutableList<String> = mutableListOf<String>()
    private val corrects : MutableList<String> = mutableListOf<String>()
    private val options : MutableList<String> = mutableListOf<String>()
    public fun fillQuiz() {
        var lines:List<String> = File("Questions.txt").readLines()
        lines.forEach{ line -> questions.add(line)}

        File("Corrects.txt").readLines().forEach {
            corrects.add(it)
        }
        File("Options.txt").readLines().forEach {
            options.add(it)
        }
    }

    public fun getQuestions() : MutableList<String>{
        return questions
    }
}

