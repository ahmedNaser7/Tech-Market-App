package com.example.techmarket.latech.presentation.admin

import com.example.techmarket.latech.domain.model.Product


data class AdminState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val signOut: Boolean = false,
    val error:String?=null,
    val products: List<Product> = emptyList(),
    val orders: List<Order> = emptyList(),
)
