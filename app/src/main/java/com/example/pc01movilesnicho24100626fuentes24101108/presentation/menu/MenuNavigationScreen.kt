package com.example.pc01movilesnicho24100626fuentes24101108.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pc01movilesnicho24100626fuentes24101108.presentation.navigation.NavScreen

@Composable
fun MenuNavigationScreen(onNavigate: (NavScreen) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Mi Aplicación",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 48.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Destinations Button
        Button(
            onClick = { onNavigate(NavScreen.DESTINATIONS) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "🌍 Destinos Turísticos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Luggage Validator Button
        Button(
            onClick = { onNavigate(NavScreen.LUGGAGE) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = "✈️ Validador de Maleta",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
