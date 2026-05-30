package com.example.pc01movilesnicho24100626fuentes24101108.presentation.destinations

data class Destination(
    val country: String,
    val city: String,
    val averageCost: Double,
    val imageUrl: String
)

object DestinationRepository {
    fun getDestinations(): List<Destination> = listOf(
        Destination(
            country = "Ucrania",
            city = "Kiev",
            averageCost = 1500.0,
            imageUrl = "https://flagcdn.com/160x120/ua.png"
        ),
        Destination(
            country = "Nigeria",
            city = "Abuya",
            averageCost = 1200.0,
            imageUrl = "https://flagcdn.com/160x120/ng.png"
        ),
        Destination(
            country = "Argentina",
            city = "Bariloche",
            averageCost = 1100.0,
            imageUrl = "https://flagcdn.com/160x120/ar.png"
        ),
        Destination(
            country = "Canadá",
            city = "Toronto",
            averageCost = 1800.0,
            imageUrl = "https://flagcdn.com/160x120/ca.png"
        ),
        Destination(
            country = "Brasil",
            city = "Río de Janeiro",
            averageCost = 900.0,
            imageUrl = "https://flagcdn.com/160x120/br.png"
        ),
        Destination(
            country = "Estados Unidos",
            city = "California",
            averageCost = 800.0,
            imageUrl = "https://flagcdn.com/160x120/us.png"
        ),
        Destination(
            country = "Perú",
            city = "Lima",
            averageCost = 1000.0,
            imageUrl = "https://flagcdn.com/160x120/pe.png"
        )
    )
}
