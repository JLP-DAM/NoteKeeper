package com.notekeeper

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var menuCardView = findViewById<CardView>(R.id.menu)
        var menuImageButton = findViewById<ImageView>(R.id.menu_button)

        menuImageButton.setOnClickListener {
            Log.d("OKAY", "THIS WORKS");
            menuCardView.animate().scaleX(300.0f)
            menuCardView.width = 1
            // 😭
        }
    }
}