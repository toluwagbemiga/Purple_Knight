package com.bit.purple.project.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bit.purple.project.ui.screens.Splash.SplashScreen


object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val SPLASH = "splash"
}

@Composable
fun AppNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH, // Set your starting screen
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }
    }
}