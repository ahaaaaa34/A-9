package com.ninimaths.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ninimaths.app.data.QuizSettings
import com.ninimaths.app.ui.home.HomeScreen
import com.ninimaths.app.ui.quiz.QuizScreen
import com.ninimaths.app.ui.records.RecordsScreen
import com.ninimaths.app.ui.theme.NinimathsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NinimathsTheme {
                val navController = rememberNavController()
                var quizSettings by remember { mutableStateOf(QuizSettings()) }

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onStartQuiz = { settings ->
                                quizSettings = settings
                                navController.navigate("quiz")
                            },
                            onRecords = { navController.navigate("records") }
                        )
                    }
                    composable("records") {
                        RecordsScreen(onHome = { navController.popBackStack() })
                    }
                    composable("quiz") {
                        QuizScreen(
                            settings = quizSettings,
                            onClose = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
