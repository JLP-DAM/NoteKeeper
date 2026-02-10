package com.notekeeper

// Esto es un "Singleton" que sirve para guardar los datos
object UserRepository {
    var emailGuardado: String? = null

    // Función para registrar al usuario en la base de datos
    fun registrarUsuario(email: String, pass: String) {
        emailGuardado = email
    }
}