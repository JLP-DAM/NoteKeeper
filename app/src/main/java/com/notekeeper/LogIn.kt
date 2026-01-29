package com.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels

class LogIn : Fragment () {

    private val currentSession: Session by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etEmail = view.findViewById<EditText>(R.id.txtEmail)
        val etPassword = view.findViewById<EditText>(R.id.txtPassword)
        val btnLogIn = view.findViewById<Button>(R.id.btnLogIn)
        val btnInciarSession = view.findViewById<Button>(R.id.btnInciarSession)

        // Ens asegurem de que hi hagi un registre fet (serà diferent quan hi hagi la base de dades)
        // I ens asegurem de que l'email i la contrasenya son els mateixos del registre
        // Si esta tot en el seu lloc, farem un log in, si no, un toast d'error apareixerà
        btnLogIn.setOnClickListener {
            if (currentSession.email.value == "") {
                Toast.makeText(context, "No hi ha sessió per iniciar - Considera Registrarte", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            if (etEmail.text.toString() != currentSession.email.value) {
                Toast.makeText(context, "Inici de Sessió Fallat - Email Incorrecte", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            if (etPassword.text.toString() != currentSession.password.value) {
                Toast.makeText(context, "Inici de Sessió Fallat - Contrasenya Incorrecte", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            currentSession.setIsLoggedIn(true)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Profile())
                .addToBackStack(null)
                .commit()
        }

        btnInciarSession.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SignIn())
                .addToBackStack(null)
                .commit()
        }
    }
}