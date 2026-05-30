package com.example.pc01movilesnicho24100626fuentes24101108.presentation.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

@Composable
fun budgetPlanner() {
    var dias by remember { mutableStateOf("") }
    var presupuestoDiario by remember { mutableStateOf("") }
    var tipoAlojamiento by remember { mutableStateOf("Estándar") }
    var expandedDropdown by remember { mutableStateOf(false) }
    var presupuestoTotal by remember { mutableStateOf("") }
    var mensajeResultado by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf("") }

    val tiposAlojamiento = mapOf(
        "Económico" to 0.8,
        "Estándar" to 1.0,
        "Premium" to 1.5
    )

    Column(
        modifier = Modifier
            .padding(32.dp, 64.dp)
            .fillMaxWidth()
    ) {
        Text(
            "Planificador de Presupuesto de Viaje",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de días
        OutlinedTextField(
            value = dias,
            onValueChange = { dias = it },
            label = { Text("Cantidad de días") },
            placeholder = { Text("Ej: 7") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de presupuesto diario
        OutlinedTextField(
            value = presupuestoDiario,
            onValueChange = { presupuestoDiario = it },
            label = { Text("Presupuesto diario") },
            placeholder = { Text("Ej: 100") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para tipo de alojamiento
        OutlinedTextField(
            value = tipoAlojamiento,
            onValueChange = { },
            label = { Text("Tipo de alojamiento") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandedDropdown = true },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Desplegar opciones"
                )
            }
        )

        DropdownMenu(
            expanded = expandedDropdown,
            onDismissRequest = { expandedDropdown = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            tiposAlojamiento.keys.forEach { tipo ->
                DropdownMenuItem(
                    text = { Text(tipo) },
                    onClick = {
                        tipoAlojamiento = tipo
                        expandedDropdown = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de calcular
        Button(
            onClick = {
                mostrarError = ""
                presupuestoTotal = ""
                mensajeResultado = ""

                when {
                    dias.isBlank() || presupuestoDiario.isBlank() -> {
                        mostrarError = "⚠ Todos los campos son obligatorios"
                    }
                    dias.toDoubleOrNull() == null || presupuestoDiario.toDoubleOrNull() == null -> {
                        mostrarError = "⚠ Ingresa valores numéricos válidos"
                    }
                    dias.toDouble() <= 0 -> {
                        mostrarError = "⚠ Los días deben ser mayores a cero"
                    }
                    presupuestoDiario.toDouble() <= 0 -> {
                        mostrarError = "⚠ El presupuesto debe ser mayor a cero"
                    }
                    else -> {
                        val diasValue = dias.toDouble()
                        val presupuesto = presupuestoDiario.toDouble()
                        val factor = tiposAlojamiento[tipoAlojamiento] ?: 1.0

                        val total = diasValue * presupuesto * factor
                        val decimalFormat = DecimalFormat("#.##")
                        presupuestoTotal = decimalFormat.format(total)

                        mensajeResultado = construirMensaje(tipoAlojamiento, diasValue, presupuesto, total)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Presupuesto")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Mostrar errores
        if (mostrarError.isNotEmpty()) {
            Text(
                mostrarError,
                color = Color.Red,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Mostrar resultados
        if (presupuestoTotal.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Presupuesto Total",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "$$presupuestoTotal",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 32.sp,
                color = Color(0xFF4CAF50)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                mensajeResultado,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun construirMensaje(tipo: String, dias: Double, presupuesto: Double, total: Double): String {
    return when (tipo) {
        "Económico" -> {
            "Viaje económico de $dias días con presupuesto de $$presupuesto diarios. " +
                    "Disfrutando de alojamientos económicos, espera ahorrar costos significativos. " +
                    "Total estimado: $${"%.2f".format(total)}"
        }
        "Estándar" -> {
            "Viaje estándar de $dias días con presupuesto de $$presupuesto diarios. " +
                    "Con alojamientos de calidad media, tendrás un balance perfecto entre confort y costo. " +
                    "Total estimado: $${"%.2f".format(total)}"
        }
        "Premium" -> {
            "Viaje de lujo de $dias días con presupuesto de $$presupuesto diarios. " +
                    "Disfrutando de alojamientos premium, vivirás una experiencia de primera clase. " +
                    "Total estimado: $${"%.2f".format(total)}"
        }
        else -> "Presupuesto calculado: $${"%.2f".format(total)}"
    }
}