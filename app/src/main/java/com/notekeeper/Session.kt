package com.notekeeper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Singleton a on tenim la informació de la Sessió actual en forma de livedata
// Representa l'email, password i si la persona esta logged in o no
class Session: ViewModel() {
    private val _email = MutableLiveData<String>("")
    private val _password = MutableLiveData<String>("")

    private val _isLoggedIn = MutableLiveData<Boolean>(false)

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        _isLoggedIn.value = isLoggedIn
    }
}


