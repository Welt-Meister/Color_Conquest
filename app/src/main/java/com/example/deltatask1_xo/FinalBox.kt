package com.example.deltatask1_xo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinalBox : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_final_box)
        val intent = intent
        val Winner_name = intent.getStringExtra("Winner_Name")
        val display = findViewById<TextView>(R.id.textView4)
        display.text = ("${Winner_name} Wins " ).toString().uppercase()
        val button_home = findViewById<Button>(R.id.button5)
        button_home.setOnClickListener{
            val intent = Intent(this, nameentering::class.java).apply {
            }
            startActivity(intent)
        }


    }
}