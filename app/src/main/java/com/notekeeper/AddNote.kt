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

        val BtnShare = findViewById<Button>(R.id.btn_nShare)
        val BtnNormal = findViewById<Button>(R.id.btn_nNormal)

        BtnShare.setOnClickListener {
            val intent = Intent(this, Share::class.java)
            startActivity(intent)
        }

        BtnNormal.setOnClickListener {
            val intent : Intent(this,note)
        }
    }
}