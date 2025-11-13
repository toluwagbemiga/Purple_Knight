package com.bit.purple.project.ui.screens.Auth.SignUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignUpScreenState())
    val state: StateFlow<SignUpScreenState> = _state.asStateFlow()


    // --- Validation Logic ---

    private fun isPasswordCriteriaMet(password: String): Boolean {
        if (password.length < 8) return false
        if (!password.contains(Regex(".*[A-Z].*"))) return false // At least one uppercase
        if (!password.contains(Regex(".*[a-z].*"))) return false // At least one lowercase
        if (!password.contains(Regex(".*[0-9].*"))) return false // At least one number
        if (!password.contains(Regex(".*[!@#$%^&+=].*"))) return false // At least one special character
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
                isSignUpButtonEnabled = isEmailValid && currentState.isPasswordValid
            )
        }
    }


    fun onFullNameChange(newFullName: String) {
        _state.update { currentState ->
            currentState.copy(
                full_name = newFullName
            )
        }
    }

    fun onPasswordChange(newPassword: String) {
        val isPasswordValid = isPasswordCriteriaMet(newPassword)
        _state.update { currentState ->
            currentState.copy(
                password = newPassword,
                isPasswordValid = isPasswordValid,
                isSignUpButtonEnabled = currentState.isEmailValid && isPasswordValid
            )
        }
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _state.update { currentState ->
            // 1. Calculate validation status outside of the copy() call
            val isConfirmPasswordValid = currentState.password == newConfirmPassword

            currentState.copy(
                passwordConfirmation = newConfirmPassword,
                // 2. Pass the calculated value to the state property (comma removed)
                isConfirmPasswordValid = isConfirmPasswordValid,

                // 3. Update button enablement using the new state property
                isSignUpButtonEnabled = currentState.isEmailValid &&
                        currentState.isPasswordValid &&
                        isConfirmPasswordValid // Use the newly calculated value
            )
        }
    }

    fun onSignupClick() {
        // Handle actual Signup logic here (API calls, database checks, etc.)
        viewModelScope.launch {
            // Placeholder for Signup success/failure
            println("Attempting Signup for: ${_state.value.email}")
        }
    }
}