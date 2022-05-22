package com.example.citizen_care

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.IOException
import java.io.InputStream

class Survey : AppCompatActivity() {
    private lateinit var myInputStream: InputStream
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        database = FirebaseDatabase.getInstance("https://citizen-care-default-rtdb.europe-west1.firebasedatabase.app").reference
        val button = findViewById<Button>(R.id.Survbtn)
        val sfl = getText("Stories.txt")

        val survText : MutableList<String> = mutableListOf<String>()
        sfl?.lines()?.forEach {
            survText.add(it)
        }

        var index = 0
        findViewById<TextView>(R.id.survQuestion)?.text = survText[index].toString()
        index++
        button.setOnClickListener{
            if(index == survText.size-1) {
                finish()
            }

            findViewById<TextView>(R.id.survQuestion)?.text = survText[index].toString()

            if(index == survText.size-2) {
                it.findViewById<Button>(R.id.Survbtn).text = "Finish"
            }

            database.child("Survey").child((index-1).toString()).setValue(((findViewById<RatingBar>(R.id.ratingBar2).rating)*2).toInt())

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