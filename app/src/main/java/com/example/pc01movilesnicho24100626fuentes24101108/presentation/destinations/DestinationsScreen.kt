package com.example.pc01movilesnicho24100626fuentes24101108.presentation.destinations

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationsScreen(onBackClick: () -> Unit) {
    val destinations = DestinationRepository.getDestinations()
    val totalCost = destinations.sumOf { it.averageCost }
    val totalDestinations = destinations.size

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Destinos Populares") },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(destinations) { destination ->
                DestinationCard(destination = destination)
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                SummaryCard(
                    totalDestinations = totalDestinations,
                    totalCost = totalCost
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun DestinationCard(destination: Destination) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = destination.imageUrl,
                    contentDescription = "${destination.city}, ${destination.country}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "País:",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = destination.country,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ciudad:",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = destination.city,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Costo Promedio:",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "$${String.format("%.2f", destination.averageCost)}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun SummaryCard(totalDestinations: Int, totalCost: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "RESUMEN FINAL",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total de Destinos",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = totalDestinations.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 28.sp
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .height(50.dp)
                        .width(2.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Suma Total",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = "$${String.format("%.2f", totalCost)}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}
