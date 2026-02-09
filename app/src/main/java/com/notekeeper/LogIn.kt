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

        //addTextChangedListener sirve para leer lo que el usario pone
        //toString convierte en String lo que hemos leido con addTextChangedListener
        //es importante hacerlo ya que si no lo hacemos nos dara la dirección donde se guarda el String y no se podra comparar luego en el ViewModel
        //it se pone coger lo que esta poniendo el usuario en el momento aunque tmb se puede poner etEmail

        etEmail.addTextChangedListener {
            logInViewModel.onLoginChanged(it.toString(), etPassword.text.toString())
        }

        etPassword.addTextChangedListener {
            logInViewModel.onLoginChanged(etEmail.text.toString(), it.toString())
        }

        //Aquí miramos si el LiveData nos dice que el botón tiene que estar encendido o apagado
        logInViewModel.isLoginEnabled.observe(viewLifecycleOwner) { activo ->
            btnLogIn.isEnabled = activo
        }

        //Si el email cambia en el ViewModel, aquí lo pillamos y cambiamos el texto del label
        logInViewModel.emailLabelText.observe(viewLifecycleOwner) { nuevoTexto ->
            tvEmailLabel.text = nuevoTexto
        }
        //Lo mismo pero para el color, si está mal se pone rojo y si está bien verde
        logInViewModel.emailLabelColor.observe(viewLifecycleOwner) { nuevoColor ->
            tvEmailLabel.setTextColor(nuevoColor)
        }

        // Lo mismo para el texto de la contraseña, para que el usuario sepa si es segura
        logInViewModel.passLabelText.observe(viewLifecycleOwner) { nuevoTexto ->
            tvPassLabel.text = nuevoTexto
        }
        // Cambiamos el color de la contraseña (rojo si es débil, verde si es fuerte)
        logInViewModel.passLabelColor.observe(viewLifecycleOwner) { nuevoColor ->
            tvPassLabel.setTextColor(nuevoColor)
        }

        //Cuando le das al botón de LogIn y todo está bien, te manda a la pantalla de tu Perfil
        btnLogIn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Profile())
                .addToBackStack(null)
                .commit()
        }

        //Si no tienes cuenta y le das a este botón, te lleva a la pantalla para registrarte
        btnInciarSession.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SignIn())
                .addToBackStack(null)
                .commit()
        }
    }
}