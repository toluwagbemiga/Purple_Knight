package com.bit.purple.project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.bit.purple.project.R
import com.bit.purple.project.ui.theme.focusedTextField
import com.bit.purple.project.ui.theme.unfocusedTextField

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorMessage: String,
    modifier: Modifier = Modifier,
    isPasswordField: Boolean = false,
    passwordVisible: Boolean = false,
    onVisibilityToggle: () -> Unit = {},
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Softer color
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = focusedTextField,
            unfocusedContainerColor = unfocusedTextField,
            focusedBorderColor = focusedTextField,
            unfocusedBorderColor = unfocusedTextField
        ),
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPasswordField) KeyboardType.Password else KeyboardType.Text
        ),
        visualTransformation = when {
            isPasswordField && passwordVisible -> VisualTransformation.None
            isPasswordField -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        trailingIcon = {
            if (isPasswordField) {
                val image = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                Icon(
                    imageVector = image,
                    contentDescription = if (passwordVisible) stringResource(id = R.string.cd_visibility_off) else stringResource(id = R.string.cd_visibility_on),
                    modifier = Modifier.clickable(onClick = onVisibilityToggle)
                )
            }
        },
        supportingText = {
            if (isError) {
                Text(text = errorMessage)
            }
        }
    )
}
