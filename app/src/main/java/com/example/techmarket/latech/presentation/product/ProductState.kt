package com.example.techmarket.latech.presentation.product

import com.example.techmarket.latech.domain.model.Product

data class ProductState(
    val selectedProduct: Product?=null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAddedToCart: Boolean = false
)
