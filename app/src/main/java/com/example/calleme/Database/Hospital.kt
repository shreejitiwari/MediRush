package com.example.calleme.Database

import kotlinx.serialization.Serializable

@Serializable
data class Hospital(
    val name: String,
    val address: String,
    val phone: String,
    val latitude: Double,
    val longitude: Double
)
