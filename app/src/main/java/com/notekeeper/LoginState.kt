package com.notekeeper

data class LoginState(
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoginSuccess: Boolean = false,
    val errorMessage: String? = null
)