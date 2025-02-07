package com.example.techmarket.latech.data.dataSource.account

import android.util.Log
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.core.domain.datasource.UserPreferencesRepository
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.account.ProfileRepository
import io.github.jan.supabase.auth.auth
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.error.UserAppPreferencesError
import com.example.techmarket.core.domain.util.onError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.domain.model.Profile
import com.example.techmarket.latech.domain.model.toProfile
import kotlinx.coroutines.flow.first


class ProfileRepositoryImpl(
    private val supabase: Supabase,
    private val appPreferencesDataSource: AppPreferencesDataSource,
    private val userPreferencesRepository: UserPreferencesRepository
): ProfileRepository{
    override suspend fun getAccountInfo(): Result<Profile, UserAppPreferencesError> {
        userPreferencesRepository.getUserDetails().onSuccess {
            Log.d("UserDetails", "getAccountInfo: ${it.first()}")
            return Result.Success(it.first().toProfile())
        }.onError {
            return Result.Error(it)
        }
        return Result.Error(UserAppPreferencesError.UserNotFound)
    }

    override suspend fun logOut(): Result<Boolean,AuthError> {
        try {
            appPreferencesDataSource.saveLoginState(false)
            appPreferencesDataSource.saveRoleState(null)
            userPreferencesRepository.clearUserDetails()
            Log.d("LogOut", "logOut: ${appPreferencesDataSource.isUserLoggedIn.first()}")
            supabase.createClient.auth.signOut()
            return Result.Success(true)
        }catch (e:Exception){
            return Result.Error(AuthError.UserNotFound)
        }
    }
}