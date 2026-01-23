package com.notekeeper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Herem de viewModel
class LogInViewModel : ViewModel() {

    //Guarda el email i la contrasenya cuan el usari el posa amb LiveData i la mostran
    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>("")
    val password: LiveData<String> = _password


    //Crea un boolean perque si el usuari posa bè la contrasenya i el email pugui passar o al contrari que no puegui passar
    private val _isLoginEnabled = MutableLiveData<Boolean>(false)

    //Hem fet un privat mutable que pugui cambiar en cas que cumpleix amb el requisit de la contrasenya i email
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    fun onLoginChanged(emailInput: String, passwordInput: String) {
        // Actualitzem els MutableLiveData perquè si el usari posa alguna cosa més que el tinguem
        _email.value = emailInput
        _password.value = passwordInput

        //cridem les funcions per validar el email i contrasenya
        _isLoginEnabled.value = isValidEmail(emailInput) && isStrongPassword(passwordInput)
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

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