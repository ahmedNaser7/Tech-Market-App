package com.example.techmarket.core.domain.datasource

import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.error.UserAppPreferencesError
import com.example.techmarket.latech.domain.model.user.UserDetailsPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun getUserDetails(): Result<Flow<UserDetailsPreferences>, UserAppPreferencesError>
    suspend fun updateUserDetails(userDetails: UserDetailsPreferences): Result<Boolean, UserAppPreferencesError>
    suspend fun clearUserDetails(): Result<Boolean, UserAppPreferencesError>
}