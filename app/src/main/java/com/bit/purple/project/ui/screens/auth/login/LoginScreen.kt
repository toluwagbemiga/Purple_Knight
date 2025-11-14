package com.bit.purple.project.ui.screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bit.purple.project.ui.theme.PrimaryColor

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
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
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Default.Close,
                contentDescription = stringResource(id = R.string.cd_close_button),
                tint = Color.Black,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Icon(Icons.Default.Info, contentDescription = stringResource(id = R.string.cd_info_icon), tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.login_welcome_message),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.login_prompt),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
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
            modifier = Modifier.fillMaxWidth()
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

        Spacer(modifier = Modifier.weight(1f))

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
            onClick = onLoginClick,
            enabled = state.isLoginButtonEnabled
        )
    }
}
