package com.example.citizen_care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quizButton = findViewById<Button>(R.id.button)
        quizButton.text = "Basic Quiz"
        quizButton.setOnClickListener{
            val Intent = Intent(this,BasicQuiz::class.java)
            startActivity(Intent)
        }
    }
}