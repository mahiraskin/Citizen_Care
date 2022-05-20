package com.example.citizen_care

import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.InputStream

//open?
class BasicQuiz : AppCompatActivity() {
    private lateinit var sfl : String
    private lateinit var qfl : String
    private lateinit var ofl : String
    private lateinit var cfl : String
    private lateinit var myInputStream: InputStream
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_quiz)

        val number = (0..3).random()
        if(number == 1) {
            sfl = getText("Stories.txt")
            qfl = getText("Questions.txt")
            ofl = getText("Options.txt")
            cfl = getText("Corrects.txt")

            val dialog = Context.newInstance(sfl)
            dialog.show(supportFragmentManager, "Context")

        }
        else {
            qfl = getText("Questions.txt")
            ofl = getText("Options.txt")
            cfl = getText("Corrects.txt")
            sfl = getText("Stories.txt")
        }

        val questionText = findViewById<TextView>(R.id.curQuestion)
        val op1 = findViewById<RadioButton>(R.id.op1)
        val op2 = findViewById<RadioButton>(R.id.op2)
        val options = arrayOf("a","b","c","d")


        val test = Quiz()
        test.fillQuestions(qfl)
        test.fillOptions(ofl)
        test.fillCorrects(cfl)
        //testing

        var a = 1
        test.getQuestions().forEach {
            var q = it
            op1.setOnClickListener {
                newQuestion(q,a++,op1, op2, questionText, test.getOptions())
            }

            op2.setOnClickListener {
                newQuestion(q,a++,op1, op2, questionText, test.getOptions())
            }
        }


    }

    private fun getText(fileName: String) : String {
        try {
            myInputStream = assets.open(fileName)
            val size: Int = myInputStream.available()
            val buffer = ByteArray(size)
            myInputStream.read(buffer)
            return String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "oops"
    }

    //just send the quiz instead
    private fun newQuestion(question: String, number: Int, op1: RadioButton, op2: RadioButton, questionText: TextView, options: MutableList<String>) {
        op1.isChecked = false
        op2.isChecked = false
        questionText.text = "$number - $question"
        op1.text = options[(number * 2) - 2].toString()
        op2.text = options[(number * 2) - 1].toString()
    }
}

class Quiz {
    private val questions : MutableList<String> = mutableListOf<String>()
    private val corrects : MutableList<String> = mutableListOf<String>()
    private val options : MutableList<String> = mutableListOf<String>()

    public fun fillQuestions(qString: String) = qString.lines().forEach {
        questions.add(it)
    }
    public fun fillCorrects(cString: String) = cString.lines().forEach {
        corrects.add(it)
    }
    public fun fillOptions(oString: String) = oString.lines().forEach {
        options.add(it)
    }

    public fun getQuestions() : MutableList<String>{
        return questions
    }

    public fun getCorrects() : MutableList<String>{
        return corrects
    }

    public fun getOptions() : MutableList<String>{
        return options
    }
}

