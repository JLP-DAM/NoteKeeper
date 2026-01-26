package com.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels // RECORDA IMPORTAR AIXÒ
import androidx.core.widget.addTextChangedListener

class LogIn : Fragment () {

    private val logInViewModel : LogInViewModel by viewModels()

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
        val tvEmailLabel = view.findViewById<TextView>(R.id.tvEmailLabel)
        val tvPassLabel = view.findViewById<TextView>(R.id.tvPassLabel)
        val btnLogIn = view.findViewById<Button>(R.id.btnLogIn)
        val btnInciarSession = view.findViewById<Button>(R.id.btnInciarSession)

        //addTextChangedListener lee el email o lo que le pases
        //toString le pasas con String prq si no te sale la direcion donde esta guardado
        //it se pone coger lo que esta poniendo el usuario en el momento aunque tmb puede poner etEmail

        etEmail.addTextChangedListener {
            logInViewModel.onLoginChanged(it.toString(), etPassword.text.toString())
        }

        etPassword.addTextChangedListener {
            logInViewModel.onLoginChanged(etEmail.text.toString(), it.toString())
        }

        logInViewModel.isLoginEnabled.observe(viewLifecycleOwner) { activo ->
            btnLogIn.isEnabled = activo
        }

        logInViewModel.emailLabelText.observe(viewLifecycleOwner) { nuevoTexto ->
            tvEmailLabel.text = nuevoTexto
        }
        logInViewModel.emailLabelColor.observe(viewLifecycleOwner) { nuevoColor ->
            tvEmailLabel.setTextColor(nuevoColor)
        }

        logInViewModel.passLabelText.observe(viewLifecycleOwner) { nuevoTexto ->
            tvPassLabel.text = nuevoTexto
        }
        logInViewModel.passLabelColor.observe(viewLifecycleOwner) { nuevoColor ->
            tvPassLabel.setTextColor(nuevoColor)
        }

        btnLogIn.setOnClickListener {
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