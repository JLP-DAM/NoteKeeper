package com.notekeeper

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import kotlin.getValue

class SignIn : Fragment() {

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var emailTextView: TextView
    lateinit var passwordTextView: TextView

    private val currentSession: Session by activityViewModels()

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
            // Definim l'Email i la contrasenya per la Sessió actual si estan d'acord amb el
            // filtre, obviament ho cambiarem quan fem el backend amb la base de
            // dades
            if (!validateEmail()) {
                Toast.makeText(context, "Registre Fallat - Email Invalid", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            if (!validatePassword()) {
                Toast.makeText(context, "Registre Fallat - Contrasenya Invalida", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            currentSession.setEmail(emailEditText.text.toString())
            currentSession.setPassword(passwordEditText.text.toString())

            //Permite passar de un fragment a otro
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LogIn())
                .addToBackStack(null)
                .commit()
        }

        emailEditText.doAfterTextChanged {
            validateEmail()
        }

        passwordEditText.doAfterTextChanged {
            validatePassword()
        }

        return view
    }

    // Validem si el correu es funcional o no i tornem el valor
    // També representem si es un correu apropiat
    fun validateEmail(): Boolean {
        var validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches()

        if (validEmail) {
            emailTextView.text = "Email - Valida!"
            emailTextView.setTextColor(Color.parseColor("#2ecc71"))
        } else {
            emailTextView.text = "Email - Invalida!"
            emailTextView.setTextColor(Color.parseColor("#e74c3c"))
        }

        if (emailEditText.text.isEmpty()) {
            emailTextView.text = "Email"
            emailTextView.setTextColor(Color.parseColor("#ffffff"))
        }

        return validEmail
    }

    // Validem si la contrasenya es valida o no, retornant la seva validesa
    // També representem si es una contrasenya suficientment forta
    fun validatePassword(): Boolean {
        var password = passwordEditText.text.toString()

        var passwordIssues = ArrayList<String>()

        if (password.length < 8) {
            passwordIssues.add("Massa curta")
        }

        if ("[A-Z]".toRegex().find(password) == null) {
            passwordIssues.add("Sense majuscula")
        }

        if ("[a-z]".toRegex().find(password) == null) {
            passwordIssues.add("Sense minuscula")
        }

        if ("[1-9]".toRegex().find(password) == null) {
            passwordIssues.add("Sense nombre")
        }

        if (passwordIssues.isEmpty()) {
            passwordTextView.text = "Password - Valid!"
            passwordTextView.setTextColor(Color.parseColor("#2ecc71"))
        } else {
            var issues = "Password - Invalida!"

            for (index in 0..(passwordIssues.lastIndex)) {
                val issue = passwordIssues.get(index)

                issues = issues + when {
                    index == 0 -> " ${issue}"
                    index < passwordIssues.lastIndex -> ", ${issue}"
                    else -> ", ${issue}!"
                }
            }

            passwordTextView.text = issues
            passwordTextView.setTextColor(Color.parseColor("#e74c3c"))
        }

        if (password.isEmpty()) {
            passwordTextView.text = "Password"
            passwordTextView.setTextColor(Color.parseColor("#ffffff"))
        }

        return passwordIssues.isEmpty()
    }
}