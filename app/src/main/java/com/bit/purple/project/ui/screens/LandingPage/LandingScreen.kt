package com.bit.purple.project.ui.screens.LandingPage



import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bit.purple.project.R
import com.bit.purple.project.ui.theme.PrimaryColor

@Composable
fun LandingScreen(navController: NavController) {
        Landing()
}
@Preview
@Composable
fun Landing(){
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier.size(150.dp),
            contentDescription = "Logo"
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Get Your Money",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )
        Text(text = "Under Control",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
            )
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = "Manage your money Seemlessly",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
            )
        Spacer(modifier = Modifier.height(250   .dp))
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(0.9f).height(50.dp)
        ) {
            Text(text = "Get Started",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                )
        }
        Spacer(modifier = Modifier.height(40.dp))

        val signupText1 = "Already have an account? "
        val signupText2 = "Sign In"

        val CombinedTextStyled = buildAnnotatedString{
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            ){
                append(signupText1)

            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
            )){
                append(signupText2)
            }
        }


        Text(text = CombinedTextStyled)
    }
}


