package com.notekeeper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Singleton on tenim la informació de la Sessió actual amb forma de livedata
object Session: ViewModel() {
    private val _email = MutableLiveData<String>("")
    private val _password = MutableLiveData<String>("")

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setEmail(email: String) {
        _email.value = email
    }
}