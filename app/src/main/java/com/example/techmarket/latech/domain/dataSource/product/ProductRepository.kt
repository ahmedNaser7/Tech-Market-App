package com.example.techmarket.latech.domain.dataSource.product


import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.latech.domain.model.Product



interface ProductRepository {
    suspend fun addProductToCart(product: Product): Result<Boolean,AuthError>
}