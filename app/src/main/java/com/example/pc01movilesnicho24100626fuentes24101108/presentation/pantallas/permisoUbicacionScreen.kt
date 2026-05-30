package com.example.pc01movilesnicho24100626fuentes24101108.presentation.pantallas

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun permisoUbicacionScreen() {
    var permisoEstado by remember { mutableStateOf("PENDIENTE") }
    var mensajePermiso by remember { mutableStateOf("") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permisoEstado = "CONCEDIDO"
            mensajePermiso = "✓ Permiso de ubicación concedido exitosamente"
        } else {
            permisoEstado = "DENEGADO"
            mensajePermiso = "✗ Permiso de ubicación denegado por el usuario"
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                "Permisos de Ubicación",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                "Estado del Permiso:",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            val colorEstado = when (permisoEstado) {
                "CONCEDIDO" -> Color(0xFF4CAF50)
                "DENEGADO" -> Color(0xFFFF5252)
                else -> Color(0xFFFFC107)
            }

            val iconoEstado = when (permisoEstado) {
                "CONCEDIDO" -> "✓ CONCEDIDO"
                "DENEGADO" -> "✗ DENEGADO"
                else -> "⏳ PENDIENTE"
            }

            Text(
                iconoEstado,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorEstado,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Permisos Solicitados:",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "• ACCESS_FINE_LOCATION: Acceso preciso a la ubicación del dispositivo (GPS)",
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "• ACCESS_COARSE_LOCATION: Acceso aproximado a la ubicación (basado en red)",
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    "Solicitar Permiso de Ubicación",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    "Solicitar Ubicación Aproximada",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (mensajePermiso.isNotEmpty()) {
                Text(
                    mensajePermiso,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = when (permisoEstado) {
                        "CONCEDIDO" -> Color(0xFF4CAF50)
                        "DENEGADO" -> Color(0xFFFF5252)
                        else -> MaterialTheme.colorScheme.onBackground
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Información:",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Los permisos de ubicación son necesarios para acceder a la posición del dispositivo. " +
                        "La aplicación utiliza la Activity Result API para manejar el permiso en tiempo de ejecución. " +
                        "Puedes revocar estos permisos desde la configuración de tu dispositivo en cualquier momento.",
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}