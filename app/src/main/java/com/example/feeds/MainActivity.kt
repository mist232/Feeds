package com.example.feeds

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = getSharedPreferences("intro1", MODE_PRIVATE)
        if (pref.getBoolean("activity_executed", false)) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        } else {
            val ed = pref.edit()
            ed.putBoolean("activity_executed", true)
            ed.apply()
        }
        setContentView(R.layout.intro1)
        val proceed = findViewById<ImageButton>(R.id.imageButton)
        val skip = findViewById<Button>(R.id.button)
        proceed.setOnClickListener { v: View? ->
            val i = Intent(this@MainActivity, Intro2::class.java)
            startActivity(i)
        }
        skip.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, Home::class.java)
            startActivity(intent)
        }
    }
}