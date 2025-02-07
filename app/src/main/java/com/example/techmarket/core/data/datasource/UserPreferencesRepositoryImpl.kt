package com.example.techmarket.core.data.datasource

import android.content.Context
import android.util.Log
import com.example.techmarket.core.data.local.userDetailsDataStore
import com.example.techmarket.core.domain.datasource.UserPreferencesRepository
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.UserAppPreferencesError
import com.example.techmarket.latech.domain.model.user.UserDetailsPreferences
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepositoryImpl(
    private val context: Context
) : UserPreferencesRepository {
    override suspend fun getUserDetails(): Result<Flow<UserDetailsPreferences>, UserAppPreferencesError> {
        try {
            val user = context.userDetailsDataStore.data
            Log.d("UserDetails", "getUserDetails: $user")
            return Result.Success(user)
        } catch (e: Exception) {
            return Result.Error(UserAppPreferencesError.UserNotFound)
        }
    }

    override suspend fun updateUserDetails(userDetails: UserDetailsPreferences): Result<Boolean, UserAppPreferencesError> {
        val updateDate = context.userDetailsDataStore.updateData {
            userDetails
        }
        if (!updateDate.email.isNullOrEmpty())
        return Result.Success(true)

        return Result.Error(UserAppPreferencesError.UserUpdateError)

    }

    override suspend fun clearUserDetails(): Result<Boolean, UserAppPreferencesError> {
        val deleteUser = context.userDetailsDataStore.updateData {
            it.toBuilder().clear().build()
        }
        if (!deleteUser.email.isNullOrEmpty())
            return Result.Success(true)

        return Result.Error(UserAppPreferencesError.UserDeleteError)
    }
}