package com.bit.purple.project.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bit.purple.project.ui.screens.LandingPage.LandingScreen
import com.bit.purple.project.ui.screens.Splash.SplashScreen
import com.bit.purple.project.ui.screens.auth.login.LoginScreen
import com.bit.purple.project.ui.screens.auth.signup.SignupScreen

@Composable
fun AppNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        //Start Screen
        startDestination = Routes.SPLASH,
        modifier = Modifier.padding(innerPadding)
    ) {
        //Splash screen
        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }
        //Landing page
        composable(Routes.LANDING_PAGE) {
            LandingScreen(navController = navController)
        }

        //Login
        composable(Routes.LOGIN){
            LoginScreen(navController = navController)
        }

        composable(Routes.SIGN_UP){
            SignupScreen(navController = navController)
        }
    }
}
