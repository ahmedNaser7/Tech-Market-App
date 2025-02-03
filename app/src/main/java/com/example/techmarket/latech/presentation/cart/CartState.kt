package com.example.techmarket.latech.presentation.cart

import com.example.techmarket.latech.domain.model.Cart
import com.example.techmarket.latech.domain.model.CartItem
import com.example.techmarket.latech.domain.model.Product

data class CartState(
    val isLoading: Boolean = false,
    val isError: String = "",
    val isSuccess: Boolean = false,
    val cartDetails: Cart?=null,
    val address: String = "Cairo",
    val products: List<Product> = emptyList()
)
