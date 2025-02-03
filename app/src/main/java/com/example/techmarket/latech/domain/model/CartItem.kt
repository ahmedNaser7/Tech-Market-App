package com.example.techmarket.latech.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: Int,
    val cartID: Int,
    val productID: Int,
    val quantity: Int,
)
