package com.bit.purple.project.ui.screens.auth.login

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isLoginButtonEnabled: Boolean = false
)
