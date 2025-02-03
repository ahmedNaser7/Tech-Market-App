package com.example.techmarket.core.data.datasource

import android.content.Context
import android.util.Log
import com.example.techmarket.core.data.local.userDetailsDataStore
import com.example.techmarket.core.domain.datasource.UserPreferencesRepository
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.latech.domain.model.user.UserDetailsPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList

class UserPreferencesRepositoryImpl(
    private val context: Context
) : UserPreferencesRepository {
    override suspend fun getUserDetails(): Result<Flow<UserDetailsPreferences>, AuthError> {
        try {
            val user = context.userDetailsDataStore.data
            Log.d("UserDetails", "getUserDetails: $user")
            return Result.Success(user)
        } catch (e: Exception) {
            return Result.Error(AuthError.UserNotFound)
        }
    }

    override suspend fun updateUserDetails(userDetails: UserDetailsPreferences): Result<Boolean, AuthError> {
        val updateDate = context.userDetailsDataStore.updateData {
            userDetails
        }
        if (!updateDate.email.isNullOrEmpty())
        return Result.Success(true)

        return Result.Error(AuthError.UserNotFound)

    }
}