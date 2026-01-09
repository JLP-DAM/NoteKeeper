package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_note)

        val btnNormal = findViewById<Button>(R.id.btn_nNormal)
        val btnShare = findViewById<Button>(R.id.btn_nShare)
        val btnAgenda = findViewById<Button>(R.id.btn_nAgenda)


        btnNormal.setOnClickListener {
            val intent = Intent(this, AddNote::class.java)
            startActivity(intent)
        }
        btnShare.setOnClickListener {
            val intent = Intent(this, Share::class.java)
            startActivity(intent)
        }
        btnAgenda.setOnClickListener {
            val intent = Intent(this, Agenda::class.java)
            startActivity(intent)
        }
    }
}