package com.bit.purple.project.ui.screens.Dashboard

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun DashboardScreen(){
    Dashboard()
}

@Composable
fun Dashboard(){

    OutlinedTextField(
        value = "Dashboard",
        onValueChange = {},
        placeholder = { Text("Search") }
    )
}