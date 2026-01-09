package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.notekeeper.R.id.btn_singup

class Share : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.share)

        val btnLogIn = findViewById<Button>(R.id.btn_login)
        val btnSignUp = findViewById<Button>(btn_singup)


        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(this)
        }
        btnLogIn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

    }
}