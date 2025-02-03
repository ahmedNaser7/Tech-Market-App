package com.example.techmarket.latech.data.dataSource.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.core.domain.datasource.UserPreferencesRepository
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.onError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.core.presentation.util.isValidEmail
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.auth.AuthRepository
import com.example.techmarket.latech.domain.model.Role
import com.example.techmarket.latech.domain.model.User
import com.example.techmarket.latech.domain.model.toUserDetailsPreferences
import com.example.techmarket.latech.domain.model.user.UserDetailsPreferences
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.toJsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class AuthRepositoryImpl(
    private val supabase: Supabase,
    private val appPreferencesDataSource: AppPreferencesDataSource,
    private val userPreferencesRepository: UserPreferencesRepository
): AuthRepository {

    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): Result<Boolean, AuthError> {
        try {
            supabase.createClient.auth.signInWith(Email){
                this.email = email
                this.password = password
            }
            appPreferencesDataSource.saveLoginState(true)
            // Todo(add password to dataStore and save to UserDetailsPrefrences)
            val userDetails = UserDetailsPreferences.newBuilder()
                .setEmail(email)
                .setName("jamal")
                .setIsAdmin(false)
                .build()
            userPreferencesRepository.updateUserDetails(userDetails).onSuccess {
                Log.d("AuthRepositoryImpl", "add user to dataStore: $it")
                return Result.Success(true)
            }.onError {
                Log.d("AuthRepositoryImpl", "add user to dataStore: ${it}")
                return Result.Error(it)
            }
        }catch (e:Exception){
            Log.d("AuthRepositoryImpl", "loginWithEmail: ${e.message}")
           return Result.Error(AuthError.LoginError)
        }
        return Result.Error(AuthError.LoginError)
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ):Result<Boolean, AuthError>{
        try {
            if(email.isValidEmail()){
                supabase.createClient.auth.signUpWith(Email){
                    this.email = email
                    this.password = password
                }

                val response =supabase.createClient.from("users")
                    .insert(
                        User(
                            id = (0..100).random(),
                            name = username,
                            email = email,
                            isAdmin = true,
                            address = "Cairo"
                        )
                    )

                Log.d("AuthRepositoryImpl", "register: $response")
                return Result.Success(true)
            }else{
                // Todo(Handle Error of login and register from regex)
            }
        }catch (e:Exception){
            Log.d("AuthRepositoryImpl", "RegisterWithEmail: ${e.message}")
            return Result.Error(AuthError.RegisterError)
        }
            return Result.Error(AuthError.RegisterError)
    }

    override suspend fun logOut():Result<Boolean, AuthError>{
        try {
            supabase.createClient.auth.signOut()
            appPreferencesDataSource.saveLoginState(false)
            return Result.Success(true)
        }catch (e:Exception){
            Log.d("AuthRepositoryImpl", "logOut: ${e.message}")
            return Result.Error(AuthError.LogOutError)
        }
    }

    override suspend fun isUserAdmin(email: String): Result<Boolean, AuthError> {
        try {
            // Todo(Handle from userProto local Model)
            val checkUserRole = supabase.createClient.from("users").select(columns = Columns.list("email","isAdmin")){
                filter {
                    eq("isAdmin",true)
                    eq("email",email)
                }
            }
            if (checkUserRole.data != "[]"){
                appPreferencesDataSource.saveRoleState(Role.Admin)
                return Result.Success(true)
            }
        }catch (e:Exception){
            return Result.Error(AuthError.UserNotFound)
        }
        return Result.Error(AuthError.UserNotFound)
    }
}