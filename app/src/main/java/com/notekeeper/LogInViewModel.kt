package com.notekeeper

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInViewModel : ViewModel() {

    //Guardamos el email cuando el usuario lo pone con LiveData
    //Tenemo la primera varibale que esta definida con _ prq nadie puede acceder des de afuera
    //La otra variable es inmutable para mostrar si el usuario puede pasar o no
    //En caso que la contraseña y email esten bien puede pasar si no no pasan hasta que no cumplen con los requisitos
    private val _isLoginEnabled = MutableLiveData<Boolean>(false)
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    //Otras variables que hacen algo parecido
    private val _emailLabelText = MutableLiveData<String>("Email")
    val emailLabelText: LiveData<String> = _emailLabelText

    private val _emailLabelColor = MutableLiveData<Int>(Color.WHITE)
    val emailLabelColor: LiveData<Int> = _emailLabelColor

    private val _passLabelText = MutableLiveData<String>("Contraseña")
    val passLabelText: LiveData<String> = _passLabelText

    private val _passLabelColor = MutableLiveData<Int>(Color.WHITE)
    val passLabelColor: LiveData<Int> = _passLabelColor


    fun onLoginChanged(emailInput: String, passwordInput: String) {

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

        //Si email y contraseña esta bien pues pueden passar
        if (emailEsValido && passEsFuerte) {
            _isLoginEnabled.value = true
        } else {
            _isLoginEnabled.value = false
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