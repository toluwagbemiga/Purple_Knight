package com.bit.purple.project.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bit.purple.project.ui.screens.Dashboard.DashboardRoute
import com.bit.purple.project.ui.screens.Dashboard.DashboardScreen
import com.bit.purple.project.ui.screens.LandingPage.LandingScreen
import com.bit.purple.project.ui.screens.Splash.SplashScreen
import com.bit.purple.project.ui.screens.auth.login.LoginScreen
import com.bit.purple.project.ui.screens.auth.signup.SignupScreen

@Composable
fun AppNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Routes.SPLASH) { SplashScreen(navController = navController) }
        composable(Routes.LANDING_PAGE) { LandingScreen(navController = navController) }
        composable(Routes.LOGIN){ LoginScreen(navController = navController) }
        composable(Routes.SIGN_UP){ SignupScreen(navController = navController) }
        composable(Routes.DASHBOARD){ DashboardRoute( onNavigateToNote = { navController.navigate(Routes.DASHBOARD) }) }
    }
}
