package com.example.citizen_care

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citizen_care.databinding.ActivityRadsBinding

class Rads : AppCompatActivity() {
    private lateinit var binding: ActivityRadsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val row1 = arrayOf(binding.button00, binding.button01, binding.button02, binding.button03)
        val row2 = arrayOf(binding.button10, binding.button11, binding.button12, binding.button13)
        val row3 = arrayOf(binding.button20, binding.button21, binding.button22, binding.button23)
        val row4 = arrayOf(binding.button30, binding.button31, binding.button32, binding.button33)
        val buttArray = arrayOf(row1, row2, row3, row4)

        buttArray.forEach {
            it.forEach { button ->
                button.setOnClickListener{
                    //change color
                }
            }
        }
    }
}