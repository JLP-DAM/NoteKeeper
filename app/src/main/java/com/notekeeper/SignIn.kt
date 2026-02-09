package com.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels // Acuérdate de importar esto para usar ViewModels
import androidx.core.widget.addTextChangedListener

class SignIn : Fragment() {

    // Conectamos este fragmento con su ViewModel para que gestione los datos
    private val signInViewModel : SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etEmail = view.findViewById<EditText>(R.id.txtEmail)
        val etPassword = view.findViewById<EditText>(R.id.txtPassword)
        val tvEmailLabel = view.findViewById<TextView>(R.id.tvEmailLabel)
        val tvPassLabel = view.findViewById<TextView>(R.id.tvPassLabel)
        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)

        //addTextChangedListener sirve para leer lo que el usario pone
        //toString convierte en String lo que hemos leido con addTextChangedListener
        //es importante hacerlo ya que si no lo hacemos nos dara la dirección donde se guarda el String y no se podra comparar luego en el ViewModel
        //it se pone coger lo que esta poniendo el usuario en el momento aunque tmb se puede poner etEmail

        etEmail.addTextChangedListener {
            signInViewModel.onSignInChanged(it.toString(), etPassword.text.toString())
        }

        // Hacemos lo mismo con la contraseña para avisar al ViewModel cada vez que escriban algo
        etPassword.addTextChangedListener {
            signInViewModel.onSignInChanged(etEmail.text.toString(), it.toString())
        }

        // Nos quedamos esperando a que el ViewModel nos diga si el botón de registro debe funcionar o no
        signInViewModel.isSignInEnabled.observe(viewLifecycleOwner) { activo ->
            btnSignIn.isEnabled = activo
        }

        // Si el texto del label del email tiene que cambiar (por ejemplo a "Válido"), lo actualizamos aquí
        signInViewModel.emailLabelText.observe(viewLifecycleOwner) { nuevoTexto ->
            tvEmailLabel.text = nuevoTexto
        }

        // Y aquí cambiamos el color del texto del email según si está bien o mal
        signInViewModel.emailLabelColor.observe(viewLifecycleOwner) { nuevoColor ->
            tvEmailLabel.setTextColor(nuevoColor)
        }

        // Lo mismo para el texto de la contraseña, para que el usuario sepa si es segura
        signInViewModel.passLabelText.observe(viewLifecycleOwner) { nuevoTexto ->
            tvPassLabel.text = nuevoTexto
        }

        // Cambiamos el color de la contraseña (rojo si es débil, verde si es fuerte)
        signInViewModel.passLabelColor.observe(viewLifecycleOwner) { nuevoColor ->
            tvPassLabel.setTextColor(nuevoColor)
        }

        // Cuando el usuario le da al botón de registrarse, lo mandamos directo a su perfil
        btnSignIn.setOnClickListener {

            // Llamamos al ViewModel para que guarde los datos en el Singleton
            signInViewModel.registrarEnRepositorio(etEmail.text.toString(), etPassword.text.toString())

            // Navegamos a la pantalla de perfil
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Profile())
                .addToBackStack(null)
                .commit()
        }
    }
}