package com.example.splashscreenloginsignup

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    var currentImage = 0
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val prev = findViewById<ImageButton>(R.id.imgPrev)
        val next = findViewById<ImageButton>(R.id.imgNext)


        prev.setOnClickListener {
            val idCurrentImageString = "pic${currentImage}"
            val idCurrentImageInt = this.resources.getIdentifier(idCurrentImageString, "id",packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f
            currentImage = (3 + currentImage - 1) % 3
            val idImageToShowString = "pic${currentImage}"
            val idImageToShowInt = this.resources.getIdentifier(idImageToShowString, "id",packageName)
            image = findViewById(idImageToShowInt)
            image.alpha = 1f
        }

        next.setOnClickListener {
            val idCurrentImageString = "pic${currentImage}"
            val idCurrentImageInt = this.resources.getIdentifier(idCurrentImageString, "id",packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f
            currentImage = (3 + currentImage + 1) % 3
            val idImageToShowString = "pic${currentImage}"
            val idImageToShowInt = this.resources.getIdentifier(idImageToShowString, "id",packageName)
            image = findViewById(idImageToShowInt)
            image.alpha = 1f
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }


}