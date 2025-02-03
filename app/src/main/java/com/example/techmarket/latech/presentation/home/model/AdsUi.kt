package com.example.techmarket.latech.presentation.home.model

data class AdsUi(
    val id: Int,
    val productID: Int,
    val discountPercentage: Int,
    val isActive: Boolean,
    val title: String,
    val imageUrl: String? = null,
)
