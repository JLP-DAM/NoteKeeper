package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class NoteBin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.note_bin)

        val menuCardView = findViewById<CardView>(R.id.menu)
        val menuImageButton = findViewById<ImageButton>(R.id.menu_button)
        val closeMenuButton = findViewById<ImageButton>(R.id.menu_close_button)
        val configurationButton = findViewById<Button>(R.id.configuration)
        val shareButton = findViewById<Button>(R.id.share)

        closeMenuButton.setOnClickListener {
            menuCardView.setLayoutParams(ViewGroup.LayoutParams(dpToPx(-300f, applicationContext), applicationContext.resources.displayMetrics.heightPixels))
        }

        menuImageButton.setOnClickListener {
            menuCardView.setLayoutParams(ViewGroup.LayoutParams(dpToPx(300f, applicationContext), applicationContext.resources.displayMetrics.heightPixels))
        }

        configurationButton.setOnClickListener {
            val intent = Intent(this, Configuration::class.java)
            startActivity(intent)
        }

        shareButton.setOnClickListener {
            val intent = Intent(this, NoteBin::class.java)
            startActivity(intent)
        }
    }
}