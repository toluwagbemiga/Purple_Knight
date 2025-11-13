package com.bit.purple.project.ui.screens.Auth.Login

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bit.purple.project.ui.navigation.Routes
import com.bit.purple.project.ui.theme.PrimaryColor
import com.bit.purple.project.ui.theme.focusedTextField
import com.bit.purple.project.ui.theme.unfocusedTextField

// --- PARENT COMPOSABLE (STATE HOLDER) ---
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    // 1. Collects the state as a Compose state
    val state by viewModel.state.collectAsState()


    // Local UI state for password visibility
    var passwordVisible by remember { mutableStateOf(false) }

    LoginContent(
        navController = navController,
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = {
            viewModel.onLoginClick()
            navController.navigate("home")
        },
        passwordVisible = passwordVisible,
        onVisibilityToggle = { passwordVisible = !passwordVisible }
    )
}

// --- UI COMPOSABLE (RENDERS THE SCREEN) ---
@Composable
fun LoginContent(
    navController: NavController,
    state: LoginScreenState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    passwordVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 18.dp)
    ) {
        // Top Icons Row
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Black,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Icon(Icons.Default.Info, contentDescription = "Info", tint = Color.Black)
        }

        // --- Content ---
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Welcome Back!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer( modifier = Modifier.height(8.dp))
        Text(text = "Sign into your account",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray

        )
        Spacer(modifier = Modifier.height(32.dp))

        // --- Email Field ---
        Text(text = "Email Address",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray

        )
        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = focusedTextField,
                unfocusedContainerColor = unfocusedTextField,
                focusedBorderColor = focusedTextField,
                unfocusedBorderColor = unfocusedTextField
            ),
            isError = state.email.isNotEmpty() && !state.isEmailValid,
            supportingText = {
                if (state.email.isNotEmpty() && !state.isEmailValid) {
                    Text(text = "Please enter a valid email address.")
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Password Field with Revealer ---
        Text(text = "Password",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,

            modifier = Modifier.fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = focusedTextField, // A light gray for focus
                unfocusedContainerColor = unfocusedTextField,
                focusedBorderColor = focusedTextField,
                unfocusedBorderColor = unfocusedTextField,

            ),

            isError = state.password.isNotEmpty() && !state.isPasswordValid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                Icon(
                    imageVector = image,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    modifier = Modifier.clickable(onClick = onVisibilityToggle)
                )
            },
            supportingText = {
                // This text will remain on the default white/transparent background
                if (state.password.isNotEmpty() && !state.isPasswordValid) {
                    Text(text = "Password must meet all complexity requirements.")
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Right
        )
        {
            Text(text = "Forgot Password?",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                )
        }


        Spacer(modifier = Modifier.height(10.dp))

        val textColor = if (isChecked) Color.Black else Color.Gray

        Row(
            modifier = Modifier.fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null, // This removes the ripple effect
                    onClick = { isChecked = !isChecked } // This is the action on click
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Using Arrangement.Start for better practice
        )
        {
            Checkbox(
                checked = isChecked,
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryColor, // Checked: Your primary color
                    uncheckedColor = Color.Gray,   // Unchecked: Gray
                    checkmarkColor = Color.White
                ),
                onCheckedChange = { newState ->
                    isChecked = newState // Update state when checkbox is tapped
                }
            )
            // 💡 3. Apply the dynamic textColor to the Text composable
            Text(
                text = "Save details for Later",
                color = textColor
            )
        }



        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Signin with Biometrics"
                , modifier = Modifier.clickable {
                    navController.navigate(Routes.LOGIN)
                },
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // --- Login Button ---

        Button(onClick = onLoginClick,
            enabled = state.isLoginButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = "Continue",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

// --- PREVIEW ---
//@Preview(showBackground = true)
//@Composable
//fun LoginContentPreview() {
//    // Create a dummy state for the preview
//    val dummyState = LoginScreenState(
//        email = "test@example.com",
//        password = "Password123!",
//        isLoginButtonEnabled = true
//    )
//
//    LoginContent(
//        state = dummyState,
//        onEmailChange = {},
//        onPasswordChange = {},
//        onLoginClick = {},
//        passwordVisible = false,
//        onVisibilityToggle = {}
//    )
//}