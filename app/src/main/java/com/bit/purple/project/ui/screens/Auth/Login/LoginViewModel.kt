package com.bit.purple.project.ui.screens.Auth.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> = _state.asStateFlow()


    // --- Validation Logic ---

    private fun isPasswordCriteriaMet(password: String): Boolean {
        if (password.length < 8) return false
        if (!password.contains(Regex(".*[A-Z].*"))) return false // At least one uppercase
        if (!password.contains(Regex(".*[a-z].*"))) return false // At least one lowercase
        if (!password.contains(Regex(".*[0-9].*"))) return false // At least one number
        if (!password.contains(Regex(".*[!@#\$%^&+=].*"))) return false // At least one special character
        return true
    }

    private fun isEmailCriteriaMet(email: String): Boolean {
        // Simple check: min 5 characters, non-blank
        if (!email.contains("@")) return false
        if (!email.contains(".")) return false
        return email.length >= 5 && email.isNotBlank()
    }

    // --- Event Handlers ---

    fun onEmailChange(newEmail: String) {
        val isEmailValid = isEmailCriteriaMet(newEmail)
        _state.update { currentState ->
            currentState.copy(
                email = newEmail,
                isEmailValid = isEmailValid,
                isLoginButtonEnabled = isEmailValid && currentState.isPasswordValid
            )
        }
    }

    fun onPasswordChange(newPassword: String) {
        val isPasswordValid = isPasswordCriteriaMet(newPassword)
        _state.update { currentState ->
            currentState.copy(
                password = newPassword,
                isPasswordValid = isPasswordValid,
                isLoginButtonEnabled = currentState.isEmailValid && isPasswordValid
            )
        }
    }

    fun onLoginClick() {
        // Handle actual login logic here (API calls, database checks, etc.)
        viewModelScope.launch {
            // Placeholder for login success/failure
            println("Attempting login for: ${_state.value.email}")
        }
    }
}