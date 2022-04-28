package com.example.citizen_care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = LoginFragment()
        dialog.show(supportFragmentManager,"loginFragment")

        val quizButton = findViewById<Button>(R.id.button)
        quizButton.text = "Basic Quiz"
        quizButton.setOnClickListener{
            val intent = Intent(this,BasicQuiz::class.java)
            startActivity(intent)
        }

        val radsButton = findViewById<Button>(R.id.button2)
        radsButton.text = "RADS"
        radsButton.setOnClickListener{
            val intent2 = Intent(this,Rads::class.java)
            startActivity(intent2)
        }
    }
}