package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout
import androidx.cardview.widget.CardView

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

        val profileCardView = findViewById<CardView>(R.id.profile_card)

        val menuCardView = findViewById<CardView>(R.id.menu)
        val menuImageButton = findViewById<ImageButton>(R.id.menu_button)
        val closeMenuButton = findViewById<ImageButton>(R.id.menu_close_button)
        val configurationButton = findViewById<Button>(R.id.configuration)
        val shareButton = findViewById<Button>(R.id.share)
        val scheduleButton = findViewById<Button>(R.id.schedule)
        val profileButton = findViewById<Button>(R.id.profile)


        closeMenuButton.setOnClickListener {
            menuCardView.setLayoutParams(ViewGroup.LayoutParams(dpToPx(-300f, applicationContext), applicationContext.resources.displayMetrics.heightPixels))

            profileCardView.visibility = View.VISIBLE
        }

        menuImageButton.setOnClickListener {
            menuCardView.setLayoutParams(ViewGroup.LayoutParams(dpToPx(300f, applicationContext), applicationContext.resources.displayMetrics.heightPixels))

            profileCardView.visibility = View.INVISIBLE
        }

        configurationButton.setOnClickListener {
            val intent = Intent(this, Configuration::class.java)
            startActivity(intent)
        }

        shareButton.setOnClickListener {
            val intent = Intent(this, NoteShare::class.java)
            startActivity(intent)
        }

        scheduleButton.setOnClickListener {
            val intent = Intent(this, agenda::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, perfil::class.java)
            startActivity(intent)
        }
    }
}