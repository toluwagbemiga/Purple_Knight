
package com.bit.purple.project.ui.screens.LandingPage


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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.compose.rememberNavController
import com.bit.purple.project.R
import com.bit.purple.project.ui.navigation.Routes
import com.bit.purple.project.ui.theme.PrimaryColor

@Composable
fun LandingScreen(navController: NavController) {
    Landing(navController = navController)
}

@Preview
@Composable
fun LandingPreview(){
    Landing(navController = rememberNavController())
}

@Composable
fun Landing(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
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
        Spacer(modifier = Modifier.height(200.dp))

        // --- GET STARTED BUTTON NAVIGATION ---
        Button(
            onClick = { navController.navigate(Routes.SIGN_UP) }, // Navigates on click
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
        val clickableTag = "SIGN_IN_CLICK"

        // --- ANNOTATED STRING FOR SIGN IN TEXT ---
        val combinedTextAnnotated = buildAnnotatedString{
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
            // Push annotation before the clickable text
            pushStringAnnotation(tag = clickableTag, annotation = "Sign In")
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    // Retain the original black color, but you may want to use primary color
                    color = Color.Black
                )){
                append(signupText2)
            }
            pop()
        }

        // --- CLICKABLE TEXT NAVIGATION ---
        ClickableText(
            text = combinedTextAnnotated,
            onClick = { offset ->
                // Check if the click offset falls within the "Sign In" tag
                combinedTextAnnotated.getStringAnnotations(
                    tag = clickableTag,
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { annotation ->
                    if (annotation.tag == clickableTag) {
                        navController.navigate(Routes.LOGIN) // Navigates to the Sign In route
                    }
                }
            }
        )
    }
}
