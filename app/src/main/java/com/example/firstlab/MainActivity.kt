package com.example.firstlab

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mainButton = Color.WHITE
    private var Score = 0;
    private val colors = arrayOf(Color.RED, Color.BLUE, Color.GREEN)
    private var buttonMap = mutableMapOf(
            R.id.b1 to Color.WHITE, R.id.b2 to Color.WHITE, R.id.b3 to Color.WHITE,
            R.id.b4 to Color.WHITE, R.id.b5 to Color.WHITE, R.id.b6 to Color.WHITE,
            R.id.b7 to Color.WHITE, R.id.b8 to Color.WHITE, R.id.b9 to Color.WHITE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var start = findViewById<Button>(R.id.buttonStart)
        start.setBackgroundColor(Color.WHITE)
        initializer()
    }

    private fun initializer() {
        for ((key) in buttonMap) {
            findViewById<Button>(key).setBackgroundColor(Color.WHITE)
            buttonMap[key] = Color.WHITE
        }
        Score = 0;
        findViewById<TextView>(R.id.currentScore).text = "Your score: " + Score
        newColor()
    }

    fun resetListener(view: View) {
        initializer()
    }


    fun currentColor(){
        var textColor = ""
        if (mainButton == Color.GREEN) textColor = "Green"
        if (mainButton == Color.RED) textColor = "Red"
        if (mainButton == Color.BLUE) textColor = "Blue"
        findViewById<TextView>(R.id.textView2).text = "Current color: $textColor "
    }

    private fun finishCompare(vararg buttons: Int) {
        for(button in buttons) {
            buttonMap[button] = Color.WHITE
            findViewById<Button>(button).setBackgroundColor(Color.WHITE)
        }
        Score++
        findViewById<TextView>(R.id.currentScore).text = "Your score: " + Score
    }

    fun clickListener(view: View) {
        if (buttonMap[view.id] == Color.WHITE) {
            buttonMap[view.id] = mainButton
            view.setBackgroundColor(mainButton)
            newColor()
            buttonCheck()
        }
    }

    private fun newColor() {
        val color = colors.random()
        mainButton = color
        findViewById<Button>(R.id.buttonMain).setBackgroundColor(color)
        currentColor()
    }

    private fun buttonCheck() {
        if (buttonMap[R.id.b1] != Color.WHITE) {
            if (buttonMap[R.id.b1] == buttonMap[R.id.b2] && buttonMap[R.id.b1] == buttonMap[R.id.b3]) {
                finishCompare(R.id.b1, R.id.b2, R.id.b3)
                return
            }
            if (buttonMap[R.id.b1] == buttonMap[R.id.b4] && buttonMap[R.id.b1] == buttonMap[R.id.b7]) {
                finishCompare(R.id.b1, R.id.b4, R.id.b7)
                return
            }
        }
        if (buttonMap[R.id.b5] != Color.WHITE) {
            if (buttonMap[R.id.b5] == buttonMap[R.id.b4] && buttonMap[R.id.b5] == buttonMap[R.id.b6]) {
                finishCompare(R.id.b5, R.id.b4, R.id.b6)
                return
            }
            if (buttonMap[R.id.b5] == buttonMap[R.id.b2] && buttonMap[R.id.b5] == buttonMap[R.id.b8]) {
                finishCompare(R.id.b5, R.id.b2, R.id.b8)
                return
            }
        }
        if (buttonMap[R.id.b9] != Color.WHITE) {
            if (buttonMap[R.id.b9] == buttonMap[R.id.b7] && buttonMap[R.id.b9] == buttonMap[R.id.b8]) {
                finishCompare(R.id.b9, R.id.b7, R.id.b8)
                return
            }
            if (buttonMap[R.id.b9] == buttonMap[R.id.b3] && buttonMap[R.id.b9] == buttonMap[R.id.b6]) {
                finishCompare(R.id.b9, R.id.b3, R.id.b6)
                return
            }
        }
        if (!buttonMap.containsValue(Color.WHITE)) {
            findViewById<TextView>(R.id.currentScore).text = "You lose, your score:" + Score
        }
    }
}