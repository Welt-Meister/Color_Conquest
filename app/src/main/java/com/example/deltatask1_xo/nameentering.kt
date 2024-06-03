package com.example.deltatask1_xo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class nameentering : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nameentering)

        val startButton: Button = findViewById(R.id.button3)
        val playerName1 = findViewById<EditText>(R.id.player1name)
        val playerName2 = findViewById<EditText>(R.id.player2name)

        startButton.setOnClickListener {
            val name1 = playerName1.text.toString()
            val name2 = playerName2.text.toString()


            val intent = Intent(this, MainActivityNew::class.java).apply {
                putExtra("PLAYER_NAME_1", name1)
                putExtra("PLAYER_NAME_2", name2)
            }
            startActivity(intent)
        }
    }
}