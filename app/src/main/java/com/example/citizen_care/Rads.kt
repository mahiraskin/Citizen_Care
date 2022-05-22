package com.example.citizen_care

import android.R.attr
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.citizen_care.databinding.ActivityRadsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.abs


class Rads : AppCompatActivity() {
    private var movements: MutableList<String> = mutableListOf<String>()
    private var blocks: MutableList<Button> = mutableListOf<Button>()
    private var doors: MutableList<Button> = mutableListOf<Button>()
    private lateinit var binding: ActivityRadsBinding
    //fill the buttons and doors should be filled at next
    //value 0 door is the starting position
    //from 0 to 15
    private var curPage = "1"
    private var x1 = 0f
    private var y1 = 0f
    private var x2 = 0f
    private var y2 = 0f
    private val minDisc = 150
    private var curIndex = arrayOf<Int>(0, 0)
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val row1 = arrayOf(binding.button00, binding.button01, binding.button02, binding.button03)
        val row2 = arrayOf(binding.button10, binding.button11, binding.button12, binding.button13)
        val row3 = arrayOf(binding.button20, binding.button21, binding.button22, binding.button23)
        val row4 = arrayOf(binding.button30, binding.button31, binding.button32, binding.button33)
        val buttArray = arrayOf(row1, row2, row3, row4)

        database = FirebaseDatabase.getInstance("https://citizen-care-default-rtdb.europe-west1.firebasedatabase.app").reference
        buttArray.forEach {
            it.forEach { button ->
                button.setOnClickListener {
                    button.setBackgroundColor(Color.GRAY);
                    blocks.add(button)
                }
            }
        }
        next(buttArray, 1)
    }

    private fun move(curIndex: Array<Int>, buttArray: Array<Array<Button>>) {
        var x = curIndex[0]
        var y = curIndex[1]
        var con : Int
        movements.forEach {
            when (it) {
                "up" -> {
                    for (a in x downTo 0) {
                        x = a + 1
                        if (blocks.contains(buttArray[a][y]))
                            break
                        else if (doors.contains(buttArray[a][y]))
                            getNext(a, y, buttArray)
                        else
                            buttArray[a][y].setBackgroundColor(Color.BLUE)
                        x--
                    }
                }
                "right" -> {
                    for (a in y..3) {
                        y = a - 1
                        if(blocks.contains(buttArray[x][a]))
                            break
                        else if (doors.contains(buttArray[x][a]))
                            getNext(a, y, buttArray)
                        else
                            buttArray[x][a].setBackgroundColor(Color.BLUE)
                        y++
                    }
                }
                "down" -> {
                    for (a in x..3) {
                        x = a - 1
                        if (blocks.contains(buttArray[a][y]))
                            break
                        else if (doors.contains(buttArray[a][y]))
                            getNext(a, y, buttArray)
                        else
                            buttArray[a][y].setBackgroundColor(Color.BLUE)
                        x++
                    }
                }
                "left" -> {
                    for (a in y downTo 0) {
                        y = a + 1
                        if (blocks.contains(buttArray[x][a]))
                            break
                        else if (doors.contains(buttArray[x][a]))
                            getNext(x, a, buttArray)
                        else
                            buttArray[x][a].setBackgroundColor(Color.BLUE)
                        y--
                    }
                }
                else -> {
                    print("WoW")
                }
            }
        }
        movements.clear()
    }

    private fun getNext(x: Int, y: Int, buttArray: Array<Array<Button>>) {
        database.child("Rads").child(curPage).child(((x * 4) + y).toString()).child(buttArray[x][y].text.toString()).get().addOnSuccessListener {
            curPage = it.value.toString().split("|")[1]
            blocks.clear()
            doors.clear()
            next(buttArray, it.value.toString().split("|")[1].toInt())
        }
    }

    private fun next(buttArray: Array<Array<Button>>, b: Int) {
        var a = 0
        buttArray.forEach { array ->
            array.forEach { button ->
                database.child("Rads").child(b.toString()).child(a.toString()).get().addOnSuccessListener {
                    button.text = it.value.toString().split("|")[0].substring(
                        1, it.value.toString().split("|")[0].length - 1
                    )
                    button.setBackgroundColor(Color.MAGENTA)
                    when {
                        it.value.toString().split("|")[1] == "block" -> {
                            button.setBackgroundColor(Color.GRAY)
                            blocks.add(button)
                        }
                        it.value.toString().split("|")[1] == "start" -> {
                            button.setBackgroundColor(Color.GREEN)
                            curIndex[0] = (it.key.toString().toInt() / 4)
                            curIndex[1] = (it.key.toString().toInt() % 4)
                        }
                        it.value.toString().split("|")[1] != "0" -> {
                            button.setBackgroundColor(Color.RED)
                            doors.add(button)
                        }
                    }
                }
                a++
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            move(curIndex, buttArray)
        }, 6000)

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