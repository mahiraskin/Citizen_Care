package com.example.citizen_care

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import java.io.IOException
import java.io.InputStream

class Survey : AppCompatActivity() {
    private lateinit var myInputStream: InputStream
    private val ratings : MutableList<Int> = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        val button = findViewById<Button>(R.id.Survbtn)
        val sfl = getText("Stories.txt")

        val survText : MutableList<String> = mutableListOf<String>()
        sfl?.lines()?.forEach {
            survText.add(it)
        }

        var index = 0

        button.setOnClickListener{
            if(index == survText.size) {
                //add 10 xp points
                finish()
            }

            if(index == survText.size - 1) {
                it.findViewById<Button>(R.id.Survbtn).text = "Finish"
            }

            else {
                ratings.add(findViewById<RatingBar>(R.id.ratingBar2).rating.toInt())
            }

            findViewById<TextView>(R.id.survQuestion)?.text = survText[index].toString()
            index++
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
}