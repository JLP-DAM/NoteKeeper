package com.notekeeper

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged

class SignIn : Fragment() {

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var emailTextView: TextView
    lateinit var passwordTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)

        emailTextView = view.findViewById<EditText>(R.id.emailTitle)
        passwordTextView = view.findViewById<EditText>(R.id.passwordTitle)

        emailEditText = view.findViewById<EditText>(R.id.email)
        passwordEditText = view.findViewById<EditText>(R.id.password)

        btnSignIn.setOnClickListener {
            // Definim l'Email i la contrasenya per la Sessió actual
            // Obviament ho cambiarem quan fem el backend amb la base de
            // dades
            Session.setEmail(emailEditText.text.toString())
            Session.setPassword(passwordEditText.text.toString())

            //Permite passar de un fragment a otro
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Profile())
                .addToBackStack(null)
                .commit()
        }

        emailEditText.doAfterTextChanged {
            validateEmail()
        }

        return view
    }

    // Validem si el correu es funcional o no i tornem el valor
    fun validateEmail(): Boolean {
        var validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches()

        if (validEmail) {
            emailTextView.text = "Email - Valid!"
            emailTextView.setTextColor(Color.parseColor("#2ecc71"))
        } else {
            emailTextView.text = "Email - Invalid!"
            emailTextView.setTextColor(Color.parseColor("#e74c3c"))
        }

        if (emailEditText.text.isEmpty()) {
            emailTextView.text = "Email"
            emailTextView.setTextColor(Color.parseColor("#ffffff"))
        }

        return validEmail
    }
}