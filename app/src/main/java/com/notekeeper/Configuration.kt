package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.badge.BadgeUtils

class Configuration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.configuration)

        val goBackImageButton = findViewById<Button>(R.id.go_back)

        goBackImageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        val btnSigin = findViewById<Button>(R.id.btn_sigin)

        btnSigin.setOnClickListener {
            val intent = Intent(this, iniciar_sesion::class.java)
            startActivity(intent)
        }
    }
}