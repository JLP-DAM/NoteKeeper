package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Paint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class iniciar_sesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciar_sesion)

        val tv: TextView = findViewById(R.id.tvCreateAccount)
        tv.paintFlags = tv.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}
