package com.example.feeds

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Intro2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro2)
        val proceed = findViewById<ImageButton>(R.id.imageButton2)
        val skip = findViewById<Button>(R.id.button2)
        proceed.setOnClickListener { v: View? ->
            val i = Intent(this@Intro2, Intro3::class.java)
            startActivity(i)
        }
        skip.setOnClickListener { v: View? ->
            val intent = Intent(this@Intro2, Home::class.java)
            startActivity(intent)
        }
    }
}