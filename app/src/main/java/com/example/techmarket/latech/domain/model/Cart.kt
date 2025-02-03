package com.example.techmarket.latech.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val id: Int,
    val userID: Int,
    val totalItems: Int,
    val totalPrice: Double
)
