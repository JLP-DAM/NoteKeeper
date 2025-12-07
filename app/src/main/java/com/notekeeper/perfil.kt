package com.notekeeper

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout

class perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cerrarSesionLayout = findViewById<LinearLayout>(R.id.cerrar_sesion)

        cerrarSesionLayout.setOnClickListener {
            val intent = Intent(this, cerrar_sesion::class.java)
            startActivity(intent)

        }

        val cambiarPasswordLayout = findViewById<LinearLayout>(R.id.cambiar_password)
        cambiarPasswordLayout.setOnClickListener {
            val intent = Intent(this, edit_contrasenya::class.java) // Ajusta el nombre
            startActivity(intent)
        }

        val notificacionesSwitch = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.notificaciones_switch)
        notificacionesSwitch.setOnCheckedChangeListener { _, isChecked ->
        }

        val btnPapelera = findViewById<android.widget.ImageButton>(R.id.papelera)
        btnPapelera.setOnClickListener {
        }

        val btnMenu = findViewById<android.widget.ImageButton>(R.id.btn_menu)
        btnMenu.setOnClickListener {
        }
    }
}