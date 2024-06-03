package com.example.deltatask1_xo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivityNew : AppCompatActivity() {
    private var currentPlayer = 1  // 1 for Player 1, 2 for Player 2
    private lateinit var buttons: List<Button>

    private var player1Score = 0
    private var player2Score = 0
    var turns = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)

        val disName1 = findViewById<TextView>(R.id.textView3)
        val disName2 = findViewById<TextView>(R.id.textView2)

        val intent = intent
        val play1 = intent.getStringExtra("PLAYER_NAME_1")
        val play2 = intent.getStringExtra("PLAYER_NAME_2")



        val resetbutton: Button = findViewById(R.id.closebutton)
        resetbutton.setOnClickListener {
            turns = 0
            resetting()
        }

        disName1.text = play1?.uppercase()
        disName2.text = play2?.uppercase()

        buttons = listOf(
            findViewById(R.id.button11),
            findViewById(R.id.button12),
            findViewById(R.id.button13),
            findViewById(R.id.button14),
            findViewById(R.id.button15),
            findViewById(R.id.button21),
            findViewById(R.id.button22),
            findViewById(R.id.button23),
            findViewById(R.id.button24),
            findViewById(R.id.button25),
            findViewById(R.id.button31),
            findViewById(R.id.button32),
            findViewById(R.id.button33),
            findViewById(R.id.button34),
            findViewById(R.id.button35),
            findViewById(R.id.button41),
            findViewById(R.id.button42),
            findViewById(R.id.button43),
            findViewById(R.id.button44),
            findViewById(R.id.button45),
            findViewById(R.id.button51),
            findViewById(R.id.button52),
            findViewById(R.id.button53),
            findViewById(R.id.button54),
            findViewById(R.id.button55)
        )

        buttons.forEach { button ->
                setupClickListener(button)
            }

        updateTurnDisplay()

    }

    fun setupClickListener(button: Button) {
        button.setOnClickListener {
            if (button.text.toString() == "3") {
                updateAdjacentButtons(button.id)
                button.text = "0"
                button.setTextColor(Color.parseColor("#FFFFFF"))
                tileExpansion()
            } else {
                button.text = (button.text.toString().toInt() + 1).toString()
                if (currentPlayer == 1) {
                    button.setTextColor(Color.parseColor("#FF6961"))
                } else {
                    button.setTextColor(Color.parseColor("#87CEEB"))
                }
            }

            // Switch turn after the current player makes a move
            switchTurn()
            pointcounter()

        }
    }

    fun updateAdjacentButtons(buttonId: Int) {
        val adjacentButtons = when (buttonId) {
            R.id.button11 -> listOf(R.id.button12, R.id.button21)
            R.id.button12 -> listOf(R.id.button11, R.id.button13, R.id.button22)
            R.id.button13 -> listOf(R.id.button12, R.id.button14, R.id.button23)
            R.id.button14 -> listOf(R.id.button13, R.id.button15, R.id.button24)
            R.id.button15 -> listOf(R.id.button14, R.id.button25)
            R.id.button21 -> listOf(R.id.button11, R.id.button22, R.id.button31)
            R.id.button22 -> listOf(R.id.button21, R.id.button12, R.id.button23, R.id.button32)
            R.id.button23 -> listOf(R.id.button22, R.id.button13, R.id.button24, R.id.button33)
            R.id.button24 -> listOf(R.id.button23, R.id.button14, R.id.button25, R.id.button34)
            R.id.button25 -> listOf(R.id.button24, R.id.button15, R.id.button35)
            R.id.button31 -> listOf(R.id.button21, R.id.button32, R.id.button41)
            R.id.button32 -> listOf(R.id.button31, R.id.button22, R.id.button33, R.id.button42)
            R.id.button33 -> listOf(R.id.button32, R.id.button23, R.id.button34, R.id.button43)
            R.id.button34 -> listOf(R.id.button33, R.id.button24, R.id.button35, R.id.button44)
            R.id.button35 -> listOf(R.id.button34, R.id.button25, R.id.button45)
            R.id.button41 -> listOf(R.id.button31, R.id.button42, R.id.button51)
            R.id.button42 -> listOf(R.id.button41, R.id.button32, R.id.button43, R.id.button52)
            R.id.button43 -> listOf(R.id.button42, R.id.button33, R.id.button44, R.id.button53)
            R.id.button44 -> listOf(R.id.button43, R.id.button34, R.id.button45, R.id.button54)
            R.id.button45 -> listOf(R.id.button44, R.id.button35, R.id.button55)
            R.id.button51 -> listOf(R.id.button41, R.id.button52)
            R.id.button52 -> listOf(R.id.button51, R.id.button42, R.id.button53)
            R.id.button53 -> listOf(R.id.button52, R.id.button43, R.id.button54)
            R.id.button54 -> listOf(R.id.button53, R.id.button44, R.id.button55)
            R.id.button55 -> listOf(R.id.button54, R.id.button45)
            else -> emptyList()
        }

        adjacentButtons.forEach { id ->
            val adjButton = findViewById<Button>(id)
            adjButton.text = (adjButton.text.toString().toInt() + 1).toString()
            if (currentPlayer == 1) {
                adjButton.setTextColor(Color.parseColor("#FF6961"))
            } else {
                adjButton.setTextColor(Color.parseColor("#87CEEB"))
            }
        }
    }

    private fun switchTurn() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
        updateTurnDisplay()
    }

    private fun updateTurnDisplay() {
        val rooting: ConstraintLayout = findViewById(R.id.main)
        if (currentPlayer == 1) {
            rooting.setBackgroundColor(Color.parseColor("#FF6961")) // Red for Player 1
        } else {
            rooting.setBackgroundColor(Color.parseColor("#87CEEB")) // Blue for Player 2
        }
    }

    private fun resetting() {
        buttons.forEach { button ->
            button.text = "0"
            button.setTextColor(Color.parseColor("#FFFFFFFF"))
        }
        currentPlayer = 1
        turns = 0
        updateTurnDisplay()
        pointcounter()

    }

    fun pointcounter() {
        var p1_score = 0
        var p2_score = 0
        turns += 1

        buttons.forEach { button ->
            val color = intToHexColorString(button.currentTextColor)

            if (color == "#FFFF6961") {
                p1_score += (button.text?.toString()?.toInt() ?: 0)
            } else if (color == "#FF87CEEB") {
                p2_score += (button.text?.toString()?.toInt() ?: 0)
            }
        }

        val score_1 = findViewById<TextView>(R.id.p1score)
        score_1.text = p1_score.toString()
        val score_2 = findViewById<TextView>(R.id.p2score)
        score_2.text = p2_score.toString()

        if (turns > 2){
            if (p1_score == 0) {
                endGame("BLUE")
                resetting()
            } else if (p2_score == 0) {
                endGame("RED")
                resetting()
            }
        }
    }

    fun intToHexColorString(colorInt: Int): String {
        return String.format("#%08X", colorInt)
    }

    private fun tileExpansion() {
        buttons.forEach { button ->
            if (button.text.toString().toInt() > 3) {
                updateAdjacentButtons(button.id)
                button.text = "0"
                button.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    private fun endGame(colorname : String) {
        var winner = colorname
        val intent = Intent(this, FinalBox::class.java).apply {
            putExtra("Winner_Name", winner)
        }
        startActivity(intent)

    }

}