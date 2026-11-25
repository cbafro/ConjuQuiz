package com.example.conjuquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.conjuquiz.navigation.AppNavigation
import com.example.conjuquiz.ui.theme.ConjuQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConjuQuizTheme {
                AppNavigation()
            }
        }
    }
}