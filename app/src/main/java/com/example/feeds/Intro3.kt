package com.example.feeds

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Intro3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro3)
        val proceed = findViewById<ImageButton>(R.id.imageButton3)
        val skip = findViewById<Button>(R.id.button3)
        proceed.setOnClickListener { v: View? ->
            val i = Intent(this@Intro3, Home::class.java)
            startActivity(i)
        }
        skip.setOnClickListener { v: View? ->
            val intent = Intent(this@Intro3, Home::class.java)
            startActivity(intent)
        }
    }
}