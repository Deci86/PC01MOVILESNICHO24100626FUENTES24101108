package com.example.pc01movilesnicho24100626fuentes24101108.presentation.pantallas

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class FlightType(val displayName: String, val maxWeight: Int) {
    DOMESTIC("Nacional", 23),
    INTERNATIONAL("Internacional", 32)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuggageValidatorScreen(onBackClick: () -> Unit) {
    var weightInput by remember { mutableStateOf("") }
    var selectedFlightType by remember { mutableStateOf(FlightType.DOMESTIC) }
    var weightError by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    val weightValue = weightInput.toDoubleOrNull()
    val isValidWeight = weightValue != null && weightValue > 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Validador de Peso de Maleta") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Weight Input
            OutlinedTextField(
            value = weightInput,
            onValueChange = { newValue: String ->
                weightInput = newValue
                weightError = ""
                showResult = false
            },
            label = { Text("Peso de la maleta (kg)") },
            placeholder = { Text("Ingrese el peso") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            isError = weightError.isNotEmpty(),
            supportingText = {
                if (weightError.isNotEmpty()) {
                    Text(weightError, color = MaterialTheme.colorScheme.error)
                }
            },
            trailingIcon = {
                if (weightError.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        // Flight Type Selection
        Text(
            text = "Tipo de vuelo",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 12.dp)
        )

        // Radio Buttons for Flight Type
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            FlightType.values().forEach { flightType ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedFlightType == flightType,
                        onClick = { selectedFlightType = flightType },
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Column {
                        Text(
                            text = flightType.displayName,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Máximo: ${flightType.maxWeight} kg",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        // Validate Button
        Button(
            onClick = {
                // Validate input
                when {
                    weightInput.isBlank() -> {
                        weightError = "Campo obligatorio"
                        showResult = false
                    }
                    weightValue == null -> {
                        weightError = "Debe ser un valor numérico"
                        showResult = false
                    }
                    weightValue <= 0 -> {
                        weightError = "Debe ser mayor a cero"
                        showResult = false
                    }
                    else -> {
                        weightError = ""
                        showResult = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 24.dp)
        ) {
            Text("Validar", fontSize = 16.sp)
        }

        // Result Display
            if (showResult && isValidWeight) {
                ResultCard(
                    weight = weightValue!!,
                    flightType = selectedFlightType,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ResultCard(
    weight: Double,
    flightType: FlightType,
    modifier: Modifier = Modifier
) {
    val maxWeight = flightType.maxWeight
    val exceeds = weight > maxWeight
    val excessWeight = if (exceeds) weight - maxWeight else 0.0

    val cardBackgroundColor = if (exceeds) {
        Color(0xFFFFEBEE)
    } else {
        Color(0xFFE8F5E9)
    }

    val borderColor = if (exceeds) {
        MaterialTheme.colorScheme.error
    } else {
        Color(0xFF4CAF50)
    }

    Card(
        modifier = modifier
            .padding(top = 16.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Status
            Text(
                text = if (exceeds) "EXCEDE LÍMITE" else "DENTRO DEL LÍMITE",
                style = MaterialTheme.typography.titleLarge,
                color = if (exceeds) MaterialTheme.colorScheme.error else Color(0xFF4CAF50),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                DetailRow("Peso de la maleta", "$weight kg")
                DetailRow("Tipo de vuelo", flightType.displayName)
                DetailRow("Límite permitido", "$maxWeight kg")

                if (exceeds) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    DetailRow(
                        label = "Peso excedido",
                        value = "${"%.2f".format(excessWeight)} kg",
                        isError = true
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    isError: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isError) MaterialTheme.colorScheme.error else Color.Black,
            fontWeight = if (isError) FontWeight.Bold else FontWeight.Normal
        )
    }
}
