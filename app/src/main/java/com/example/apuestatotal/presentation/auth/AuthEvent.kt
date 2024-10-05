package com.example.apuestatotal.presentation.auth

sealed class AuthEvent {
    data class EmailChanged(val email: String) : AuthEvent()
    data class PasswordChanged(val password: String) : AuthEvent()
    data object LoginClicked : AuthEvent()
}
