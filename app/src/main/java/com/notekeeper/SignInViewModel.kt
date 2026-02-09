package com.notekeeper

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {


    // Tenemos dos variables que guardan el mismo valor
    // Una es privada y mutable, se usa dentro del ViewModel para actualizar los valores
    // Empieza con un guion bajo porque es privada y no se puede acceder desde fuera
    // La otra es pública e inmutable, y se usa para mostrar los datos al usuario
    // Ambas variables se almacenan en LiveData ya que permite guardar lo que el usuario en el momento

    private val _isSignInEnabled = MutableLiveData<Boolean>(false)
    val isSignInEnabled: LiveData<Boolean> = _isSignInEnabled

    //Otras variables que hacen algo parecido para el Email
    private val _emailLabelText = MutableLiveData<String>("Email")
    val emailLabelText: LiveData<String> = _emailLabelText

    private val _emailLabelColor = MutableLiveData<Int>(Color.WHITE)
    val emailLabelColor: LiveData<Int> = _emailLabelColor

    //Otras variables para la Contraseña
    private val _passLabelText = MutableLiveData<String>("Contraseña")
    val passLabelText: LiveData<String> = _passLabelText

    private val _passLabelColor = MutableLiveData<Int>(Color.WHITE)
    val passLabelColor: LiveData<Int> = _passLabelColor


    fun registrarEnRepositorio(email: String, pass: String) {
        // El ViewModel le dice al repositorio que guarde los datos
        UserRepository.registrarUsuario(email, pass)
    }


    fun onSignInChanged(emailInput: String, passwordInput: String) {

        val emailEsValido = emailInput.contains("@") && emailInput.contains(".")

        //Si esta en blanco el warnig se muestra en blanco
        if (emailInput.isEmpty()) {
            _emailLabelText.value = "Email"
            _emailLabelColor.value = Color.WHITE
        } else {
            //Si cumple con las condiciones el email se muestra en verde
            if (emailEsValido) {
                _emailLabelText.value = "Email - ¡Válido!"
                _emailLabelColor.value = Color.parseColor("#2ecc71")
            } else {
                //Si no en rojo
                _emailLabelText.value = "Email - Inválido"
                _emailLabelColor.value = Color.parseColor("#e74c3c")
            }
        }

        //Llamamos a la función que válida la contraseña
        val passEsFuerte = isStrongPassword(passwordInput)


        if (passwordInput.isEmpty()) {
            _passLabelText.value = "Contraseña"
            _passLabelColor.value = Color.WHITE
        } else {
            if (passEsFuerte) {
                _passLabelText.value = "Contraseña - ¡Segura!"
                _passLabelColor.value = Color.parseColor("#2ecc71")
            } else {
                _passLabelText.value = "Contraseña - Débil"
                _passLabelColor.value = Color.parseColor("#e74c3c")
            }
        }

        //Si email y contraseña esta bien pues pueden registrarse
        if (emailEsValido && passEsFuerte) {
            _isSignInEnabled.value = true
        } else {
            _isSignInEnabled.value = false
        }
    }

    //Requisito de la contraseña
    private fun isStrongPassword(pass: String): Boolean {
        if (pass.length < 8) return false
        var teMajuscula = false
        var teMinuscula = false
        var teNumero = false
        var teSimbol = false
        for (caracter in pass) {
            if (caracter.isUpperCase()) teMajuscula = true
            if (caracter.isLowerCase()) teMinuscula = true
            if (caracter.isDigit()) teNumero = true
            if (!caracter.isLetterOrDigit()) teSimbol = true
        }
        return teMajuscula && teMinuscula && teNumero && teSimbol
    }
}