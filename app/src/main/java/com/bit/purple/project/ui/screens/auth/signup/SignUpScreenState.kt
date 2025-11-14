package com.bit.purple.project.ui.screens.auth.signup

data class SignUpScreenState (
    val email: String = "",
    var full_name: String = "",
    //val phone_number: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isConfirmPasswordValid: Boolean = false,
    val isSignUpButtonEnabled: Boolean = false
)