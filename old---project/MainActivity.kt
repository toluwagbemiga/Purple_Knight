package com.bit.purple.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.bit.purple.project.ui.navigation.AppNavGraph
import com.bit.purple.project.ui.theme.PurpleKnightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PurpleKnightTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    AppNavGraph(navController = navController, innerPadding = innerPadding)

                }
            }
        }
    }
}

