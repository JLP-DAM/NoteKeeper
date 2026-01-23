package com.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels // RECORDA IMPORTAR AIXÒ
import androidx.core.widget.addTextChangedListener

class LogIn : Fragment() {

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
        val btnLogIn = view.findViewById<Button>(R.id.btnLogIn)
        val btnInciarSession = view.findViewById<Button>(R.id.btnInciarSession)

        etEmail.addTextChangedListener {
            logInViewModel.onLoginChanged(it.toString(), etPassword.text.toString())
        }

        etPassword.addTextChangedListener {
            logInViewModel.onLoginChanged(etEmail.text.toString(), it.toString())
        }

        logInViewModel.isLoginEnabled.observe(viewLifecycleOwner) { nouValor ->
            if (nouValor == true) {
                btnLogIn.isEnabled = true
            } else {
                btnLogIn.isEnabled = false
            }
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