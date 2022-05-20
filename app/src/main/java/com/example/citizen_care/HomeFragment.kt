package com.example.citizen_care

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val quizButton = view.findViewById<Button>(R.id.quizbtn)
        quizButton.text = "Basic Quiz"
        quizButton.setOnClickListener{
            val intent = Intent(activity,BasicQuiz::class.java)
            startActivity(intent)
        }

        val radsButton = view.findViewById<Button>(R.id.radsbtn)
        radsButton.text = "R.A.D.S."
        radsButton.setOnClickListener{
            val intent2 = Intent(activity,Rads::class.java)
            startActivity(intent2)
        }

        val survButton = view.findViewById<Button>(R.id.surbtn)
        survButton.text = "Survey"
        survButton.setOnClickListener{
            val intent2 = Intent(activity,Survey::class.java)
            startActivity(intent2)
        }
        return view
    }

}