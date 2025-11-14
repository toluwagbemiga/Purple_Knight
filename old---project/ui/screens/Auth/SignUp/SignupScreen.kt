package com.bit.purple.project.ui.screens.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bit.purple.project.R
import com.bit.purple.project.ui.components.AuthTextField
import com.bit.purple.project.ui.components.PrimaryButton
import com.bit.purple.project.ui.navigation.Routes
import com.bit.purple.project.ui.screens.auth.signUp.SignUpScreenState
import com.bit.purple.project.ui.theme.PrimaryColor
import com.bit.purple.project.ui.theme.SmallCorners

@Composable
fun SignupScreen(navController: NavController, viewModel: SignupViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    SignupContent(
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
fun SignupContent(
    navController: NavController,
    state: SignUpScreenState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignupClick: () -> Unit,
    passwordVisible: Boolean,
    onVisibilityToggle: () -> Unit,
    onFullNameChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.cd_back_arrow),
                tint = Color.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(SmallCorners))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { navController.popBackStack() }
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.action_back),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { navController.popBackStack() }
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.signup_get_started),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.signup_password_requirements),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(18.dp))

        AuthTextField(
            value = state.full_name,
            onValueChange = onFullNameChange,
            label = stringResource(id = R.string.signup_full_name_label),
            isError = state.full_name.isNotEmpty() && state.full_name.isBlank(),
            errorMessage = stringResource(id = R.string.error_full_name_required)
        )

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.login_email_label),
            isError = state.email.isNotEmpty() && !state.isEmailValid,
            errorMessage = stringResource(id = R.string.error_invalid_email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.login_password_label),
            isError = state.password.isNotEmpty() && !state.isPasswordValid,
            errorMessage = stringResource(id = R.string.error_password_complexity),
            isPasswordField = true,
            passwordVisible = passwordVisible,
            onVisibilityToggle = onVisibilityToggle
        )

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(
            value = state.passwordConfirmation,
            onValueChange = onConfirmPasswordChange,
            label = stringResource(id = R.string.signup_confirm_password_label),
            isError = state.passwordConfirmation.isNotEmpty() && !state.isConfirmPasswordValid,
            errorMessage = stringResource(id = R.string.error_password_complexity),
            isPasswordField = true,
            passwordVisible = passwordVisible,
            onVisibilityToggle = onVisibilityToggle
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Right
        ) {
            Text(
                text = stringResource(id = R.string.login_forgot_password),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        val textColor = if (isChecked) Color.Black else Color.Gray

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { isChecked = !isChecked }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = isChecked,
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryColor,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                ),
                onCheckedChange = { newState -> isChecked = newState }
            )
            Text(
                text = stringResource(id = R.string.login_save_details),
                color = textColor
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.login_with_biometrics),
                modifier = Modifier.clickable { navController.navigate(Routes.LOGIN) },
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        PrimaryButton(
            text = stringResource(id = R.string.action_continue),
            onClick = onSignupClick,
            enabled = state.isSignUpButtonEnabled
        )
    }
}
