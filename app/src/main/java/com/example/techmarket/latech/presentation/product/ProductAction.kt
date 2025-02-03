package com.example.techmarket.latech.presentation.product


import com.example.techmarket.latech.domain.model.Product

sealed class ProductAction{
    data class AddToCart(val product: Product): ProductAction()
}