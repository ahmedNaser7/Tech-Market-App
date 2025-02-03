package com.example.techmarket.latech.domain.dataSource.cart

import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.latech.domain.model.Cart
import com.example.techmarket.latech.domain.model.CartItem
import com.example.techmarket.latech.domain.model.Product
import kotlinx.coroutines.flow.Flow


interface CartRepository {
    suspend fun getProductsFromCartItems(): Result<List<Product>, AuthError>
    suspend fun getCartDetails():Result<Cart, AuthError>
}