package com.example.citizen_care

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import java.io.IOException
import java.io.InputStream

//open?
class BasicQuiz : AppCompatActivity() {
    private lateinit var sfl : String
    private lateinit var qfl : String
    private lateinit var ofl : String
    private lateinit var quizType : String
    private lateinit var myInputStream: InputStream
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_quiz)
        database = FirebaseDatabase.getInstance("https://citizen-care-default-rtdb.europe-west1.firebasedatabase.app").reference

        val number = (0..3).random()
        if(number == 1) {
            quizType = "Conquiz"
            qfl = getText("Questions.txt")
            ofl = getText("Options.txt")
            sfl = getText("Stories.txt")

            val dialog = Context.newInstance(sfl)
            dialog.show(supportFragmentManager, "Context")

        }
        else {
            quizType = "BasicQuiz"
            qfl = getText("Questions.txt")
            ofl = getText("Options.txt")
            sfl = getText("Stories.txt")
        }

        val questionText = findViewById<TextView>(R.id.curQuestion)
        val op1 = findViewById<RadioButton>(R.id.op1)
        val op2 = findViewById<RadioButton>(R.id.op2)


        val test = Quiz()
        test.fillQuestions(qfl)
        test.fillOptions(ofl)


        var a = 1
        var q : String

        newQuestion(test.getQuestions()[a-1],a++,op1, op2, questionText, test.getOptions())
        a++
        op1.setOnClickListener {
            if (a == test.getQuestions().size) {
                finish()
            }

            q = test.getQuestions()[a-1]
            newQuestion(q,a++,op1, op2, questionText, test.getOptions())

            database.child(quizType).child("Correct").child((a-1).toString()).get().addOnSuccessListener {
                if (op1.text == it.value.toString())
                    database.child("Profile").child("Blob3").child("Xp").setValue(ServerValue.increment(10))

            }
        }

        op2.setOnClickListener {
            if (a == test.getQuestions().size) {
                finish()
            }

            q = test.getQuestions()[a-1]
            newQuestion(q,a++,op1, op2, questionText, test.getOptions())

            database.child(quizType).child("Correct").child((a-1).toString()).get().addOnSuccessListener {
                if (op2.text == it.value.toString())
                    database.child("Profile").child("Blob3").child("Xp").setValue(ServerValue.increment(10))

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
    @SuppressLint("SetTextI18n")
    private fun newQuestion(question: String, number: Int, op1: RadioButton, op2: RadioButton, questionText: TextView, options: MutableList<String>) {
        op1.isChecked = false
        op2.isChecked = false
        questionText.text = "$number - $question"
        op1.text = options[(number * 2) - 2]
        op2.text = options[(number * 2) - 1]
    }
}

class Quiz {
    private val questions : MutableList<String> = mutableListOf()
    private val options : MutableList<String> = mutableListOf()

    fun fillQuestions(qString: String) = qString.lines().forEach {
        questions.add(it)
    }

    fun fillOptions(oString: String) = oString.lines().forEach {
        options.add(it)
    }

    fun getQuestions() : MutableList<String>{
        return questions
    }

    fun getOptions() : MutableList<String>{
        return options
    }
}

