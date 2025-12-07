package com.notekeeper

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class edit_contrasenya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_contrasenya)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnChangePassword = findViewById<Button>(R.id.loginButton)

        btnChangePassword.setOnClickListener {
            cambiarContrasenya()

            volverAlPerfil()
        }
    }

    private fun cambiarContrasenya() {

    }

    private fun volverAlPerfil() {
        val intent = Intent(this, perfil::class.java)
        startActivity(intent)
    }
}