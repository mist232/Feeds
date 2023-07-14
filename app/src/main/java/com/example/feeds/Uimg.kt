package com.example.feeds

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class Uimg : AppCompatActivity() {
    private var selectedImageBitmap: Bitmap? = null
    private val REQUEST_IMAGE_SELECT = 100

    private lateinit var imageView: ImageView
    private lateinit var select: Button

    private val imageSelectLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val selectedImageUri: Uri? = result.data?.data
            try {
                selectedImageBitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                imageView.setImageBitmap(selectedImageBitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.uimg)

        val back: ImageButton = findViewById(R.id.icon_arrow_)
        back.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Uimg, Home::class.java)
            startActivity(intent)
        })

        imageView = findViewById(R.id.image_4)
        select = findViewById(R.id.btn_primary)
        select.setOnClickListener(View.OnClickListener {
            imageChooser()
        })
    }

    private fun imageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        imageSelectLauncher.launch(intent)
    }
}
