package com.example.pc01movilesnicho24100626fuentes24101108

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pc01movilesnicho24100626fuentes24101108.presentation.pantallas.budgetPlanner
import com.example.pc01movilesnicho24100626fuentes24101108.presentation.luggage.LuggageValidatorScreen
import com.example.pc01movilesnicho24100626fuentes24101108.ui.theme.PC01MOVILESNICHOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PC01MOVILESNICHOTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LuggageValidatorScreen()
                }
            }
        }
    }
}
