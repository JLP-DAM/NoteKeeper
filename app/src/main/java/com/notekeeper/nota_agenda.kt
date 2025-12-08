package com.notekeeper

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton

class nota_agenda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nota_agenda)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnClose = findViewById<ImageButton>(R.id.btn_close)

        btnClose.setOnClickListener{
            val intent = Intent(this, agenda::class.java)
            startActivity(intent)
        }

        val btnSave = findViewById<ImageButton>(R.id.btn_save)

        btnSave.setOnClickListener {
            val intent = Intent(this, agenda::class.java)
            startActivity(intent)
        }
    }
}