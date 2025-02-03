package com.example.techmarket.latech.domain.dataSource.auth


import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.latech.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginWithEmail(email: String, password: String): Result<Boolean, AuthError>
    suspend fun register(username:String,email: String, password: String):Result<Boolean, AuthError>
    suspend fun logOut():Result<Boolean, AuthError>
    suspend fun isUserAdmin(email: String):Result<Boolean, AuthError>
}