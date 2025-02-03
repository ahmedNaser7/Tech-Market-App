package com.example.techmarket.latech.domain.dataSource.admin

import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError


interface AdminRepository {
    suspend fun addProductAdmin(index: Int, name: String, price: Double):Result<Boolean, AuthError>
    suspend fun deleteProductAdmin(productId: Int):Result<Boolean, AuthError>
    suspend fun signOutAdmin():Result<Boolean, AuthError>
}