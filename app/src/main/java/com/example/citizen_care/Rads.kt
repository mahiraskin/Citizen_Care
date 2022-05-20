package com.example.citizen_care

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.citizen_care.databinding.ActivityRadsBinding
import java.io.BufferedOutputStream
import java.io.BufferedReader
import kotlin.math.abs


class Rads : AppCompatActivity() {
    private var movements: MutableList<String> = mutableListOf<String>()
    private lateinit var binding: ActivityRadsBinding
    //fill the buttons and doors should be filled at next
    //from 0 to 15
    private var doors: MutableList<Int> = mutableListOf<Int>()
    private var x1 = 0f
    private var y1 = 0f
    private var x2 = 0f
    private var y2 = 0f
    private val minDisc = 150
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var curIndex = arrayOf<Int>(0, 0)
        val row1 = arrayOf(binding.button00, binding.button01, binding.button02, binding.button03)
        val row2 = arrayOf(binding.button10, binding.button11, binding.button12, binding.button13)
        val row3 = arrayOf(binding.button20, binding.button21, binding.button22, binding.button23)
        val row4 = arrayOf(binding.button30, binding.button31, binding.button32, binding.button33)
        val buttArray = arrayOf(row1, row2, row3, row4)
        //correct the color like this in next()
        buttArray.forEach {
            it.forEach { button ->
                button.setOnClickListener {
                    button.setBackgroundColor(Color.GRAY);
                }
            }
        }
        //next
        Handler(Looper.getMainLooper()).postDelayed({
            move(curIndex, buttArray)
        }, 6000)

    }

    private fun move(curIndex: Array<Int>, buttArray: Array<Array<Button>>) {
        //instead can just use the array
        var x = curIndex[0]
        var y = curIndex[1]
        movements.forEach {
            when (it) {
                "up" -> {
                    for(a in x downTo 0) {
                        //check
                        buttArray[a][y].setBackgroundColor(Color.BLUE)
                        x = a
                    }
                }
                "right" -> {
                    for(a in y..3) {
                        buttArray[x][a].setBackgroundColor(Color.BLUE)
                        if(a == 3) {
                            y = a
                        }
                    }
                }
                "down" -> {
                    for (a in x..3) {
                        buttArray[a][y].setBackgroundColor(Color.BLUE)
                        if(a == 3) {
                            x = a
                        }
                    }
                }
                "left" -> {
                    for (a in y downTo 0) {
                        buttArray[x][a].setBackgroundColor(Color.BLUE)
                        y = a
                    }
                }
                else -> {
                    print("WoW")
                }
            }
        }
        movements.clear()
    }
    //check if door
    private fun check(x: Int, y: Int, buttArray: Array<Array<Button>>) {
        if(doors.contains(x*4 + y)) {
            //next()
        }
    }
    //check if blue or gray
    private fun check(button: Button) : Boolean {
        var r = true
        if (button.background.equals(Color.GRAY)) {
            r = false
        }
        return r
    }

    private fun nextf(buttArray: Array<Array<Button>>) {
        doors.forEach{
            var x: Int = it/4
            var y: Int = it%4
            buttArray[x][y].setBackgroundColor(Color.GRAY)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                y1 = event.y
            }
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                y2 = event.y
                val deltaX: Float = x2 - x1
                val deltaY: Float = y2 - y1
                if (abs(deltaX) > abs(deltaY)) {
                    if (abs(deltaX) > minDisc) {
                        if (x2 > x1) {
                            movements.add("right")
                            Toast.makeText(this, "Left to Right swipe", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            movements.add("left")
                            Toast.makeText(
                                this,
                                "Right to Left swipe",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    } else {
                        Toast.makeText(this, "Swipe HARDER", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    if (abs(deltaY) > minDisc) {

                        if (deltaY < 0) {
                            movements.add("up")
                            Toast.makeText(this, "Bottom to Top", Toast.LENGTH_SHORT)
                                .show()
                        }
                        if (deltaY > 0) {
                            movements.add("down")
                            Toast.makeText(this, "Top to Bottom", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(this, "Swipe HARDER", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }
}