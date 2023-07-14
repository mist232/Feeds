package com.example.feeds

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        val feed = findViewById<Button>(R.id.btn_primary)
        feed.setOnClickListener { v: View? ->
            val i = Intent(this@Home, FeedActivity::class.java)
            startActivity(i)
        }
        val uploadButton = findViewById<Button>(R.id.btn_primary2)
        uploadButton.setOnClickListener { v: View? ->
            val i = Intent(this@Home, Uimg::class.java)
            startActivity(i)
        }
    }
}