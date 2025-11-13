package com.bit.purple.project.ui.screens.Auth.Login

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isLoginButtonEnabled: Boolean = false
)
