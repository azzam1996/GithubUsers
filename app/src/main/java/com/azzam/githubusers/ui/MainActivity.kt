package com.azzam.githubusers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.azzam.githubusers.ui.navigation.Navigation
import com.azzam.githubusers.ui.theme.Eminence
import com.azzam.githubusers.ui.theme.GithubUsersTheme
import com.azzam.githubusers.ui.theme.Valentino
import com.azzam.githubusers.ui.theme.Valentino_1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            GithubUsersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Valentino_1, Valentino, Eminence)
                                )
                            )
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}