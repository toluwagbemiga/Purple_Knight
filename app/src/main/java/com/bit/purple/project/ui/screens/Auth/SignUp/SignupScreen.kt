package com.bit.purple.project.ui.screens.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bit.purple.project.ui.navigation.Routes
import com.bit.purple.project.ui.theme.PrimaryColor
import com.bit.purple.project.ui.theme.SmallCorners
import com.bit.purple.project.ui.theme.focusedTextField
import com.bit.purple.project.ui.theme.unfocusedTextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.bit.purple.project.R
import com.bit.purple.project.ui.screens.Auth.SignUp.SignUpScreenState
import com.bit.purple.project.ui.screens.Auth.SignUp.SignupViewModel

@Composable
fun SignupScreen(navController: NavController, viewModel: SignupViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()


    // Local UI state for password visibility
    var passwordVisible by remember { mutableStateOf(false) }

    SigninContent(
        navController = navController,
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignupClick = {
            viewModel.onSignupClick()
            navController.navigate("home")
        },
        passwordVisible = passwordVisible,
        onVisibilityToggle = { passwordVisible = !passwordVisible },
        onFullNameChange = viewModel::onFullNameChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange
    )

}
@Composable
fun SigninContent(navController: NavController,
                  state: SignUpScreenState,
                  onEmailChange: (String) -> Unit,
                  onPasswordChange: (String) -> Unit,
                  onSignupClick: () -> Unit,
                  passwordVisible: Boolean,
                  onVisibilityToggle: () -> Unit,
                  onFullNameChange : (String) -> Unit,
                  onConfirmPasswordChange : (String) -> Unit
                  ){

    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 18.dp)
    ) {






        // Top Icons Row Navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            Icon(Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Close",
                tint = Color.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(SmallCorners))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        //indication = rememberRipple(bounded = true, radius = SmallCorners),
                        onClick = {
                            navController.popBackStack()
                        }
                    )
            )
            Text(text = "Back",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        navController.popBackStack()
                    }
                )
                )
        }






        // --- Content ---


        //-- Header Content --
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Lets get you started!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Your password must have at least 8 characters including Letters and Numbers",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray

        )
        Spacer(modifier = Modifier.height(18.dp))









        // --- Full Name ---
        Text(text = "Full Name",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray

        )
        OutlinedTextField(
            value = state.full_name,
            onValueChange = onFullNameChange,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = focusedTextField,
                unfocusedContainerColor = unfocusedTextField,
                focusedBorderColor = focusedTextField,
                unfocusedBorderColor = unfocusedTextField
            ),
            isError = state.full_name.isNotEmpty(),
            supportingText = {
                if (state.email.isNotEmpty() && !state.isEmailValid) {
                    Text(text = "Please enter your Full Name.")
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))











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
                    contentDescription = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(
                        R.string.show_password
                    ),
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




        // --- Confirm Password Section

        Text(text = "Confirm Password",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        OutlinedTextField(
            value = state.passwordConfirmation,
            placeholder = { Text(text = "Confirm Password") },
            onValueChange = onConfirmPasswordChange,

            modifier = Modifier.fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = focusedTextField, // A light gray for focus
                unfocusedContainerColor = unfocusedTextField,
                focusedBorderColor = focusedTextField,
                unfocusedBorderColor = unfocusedTextField,

                ),

            isError = state.passwordConfirmation.isNotEmpty() && !state.isPasswordValid,
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
            modifier = Modifier
                .fillMaxWidth()
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

        // --- Signup Button ---

        Button(onClick = onSignupClick,
            enabled = state.isSignUpButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
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