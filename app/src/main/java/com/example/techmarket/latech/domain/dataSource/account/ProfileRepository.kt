package com.example.techmarket.latech.domain.dataSource.account

import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.error.UserAppPreferencesError
import com.example.techmarket.latech.domain.model.Profile

interface ProfileRepository {
    suspend fun getAccountInfo(): Result<Profile, UserAppPreferencesError>
    suspend fun logOut(): Result<Boolean, AuthError>
}