package com.bit.purple.project.ui.screens.Splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bit.purple.project.R
import com.bit.purple.project.ui.navigation.Routes
import com.bit.purple.project.ui.theme.PrimaryColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000L)

        navController.navigate(Routes.LANDING_PAGE) {
            popUpTo(Routes.SPLASH) {
                inclusive = true
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Splash()
    }

}
@Preview
@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(PrimaryColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo_light),
            modifier = Modifier.size(150.dp),
            contentDescription = "Logo",
            tint = Color.White
        )
    }
}


