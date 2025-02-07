package com.example.techmarket.latech.data.dataSource.auth

import android.util.Log
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.core.domain.datasource.UserPreferencesRepository
import com.example.techmarket.core.domain.use_case.ValidateLoginInputCase
import com.example.techmarket.core.domain.use_case.ValidateRegisterInputCase
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.error.LoginInputValidationTypeError
import com.example.techmarket.core.domain.util.error.RegisterInputValidationTypeError
import com.example.techmarket.core.domain.util.onError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.auth.AuthRepository
import com.example.techmarket.latech.domain.model.Role
import com.example.techmarket.latech.domain.model.User
import com.example.techmarket.latech.domain.model.user.UserDetailsPreferences
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class AuthRepositoryImpl(
    private val supabase: Supabase,
    private val appPreferencesDataSource: AppPreferencesDataSource,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val validateLoginInputCase: ValidateLoginInputCase,
    private val validateRegisterInputCase: ValidateRegisterInputCase,
) : AuthRepository {

    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): Result<Boolean, LoginInputValidationTypeError> {
        try {
            // check Login Validation
            val validation = validateLoginInputCase.invoke(email, password)
            if (validation == LoginInputValidationTypeError.Valid) {
                // create login session
                supabase.createClient.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }
                // Save To DataStore
                if (saveUserDetailsToDataStore(email))
                    return Result.Success(true)
            } else if (validation == LoginInputValidationTypeError.EmptyField) {
                return Result.Error(LoginInputValidationTypeError.EmptyField)
            } else if (validation == LoginInputValidationTypeError.EmailFieldEmpty) {
                return Result.Error(LoginInputValidationTypeError.EmailFieldEmpty)
            } else if (validation == LoginInputValidationTypeError.PasswordFieldEmpty) {
                return Result.Error(LoginInputValidationTypeError.PasswordFieldEmpty)
            } else if (validation == LoginInputValidationTypeError.EmailIsNotValid) {
                return Result.Error(LoginInputValidationTypeError.EmailIsNotValid)
            }
            // handle Result.Error(LoginInputValidationTypeError.Email IS Not Found in Database)
        } catch (e: Exception) {
            return Result.Error(LoginInputValidationTypeError.EmailNotMatchWithPassword)
        }
        return Result.Error(LoginInputValidationTypeError.UnknownLoginError)
    }


    override suspend fun register(
        username: String,
        email: String,
        password: String,
        address: String
    ): Result<Boolean, RegisterInputValidationTypeError> {
        try {
            val validation = validateRegisterInputCase.invoke(email, password)
            if (validation == RegisterInputValidationTypeError.Valid) {
                supabase.createClient.auth.signUpWith(Email) {
                    this.email = email
                    this.password = password
                }
                createUserInSupabaseDatabase(username, email, address)
                return Result.Success(true)
            } else if (validation == RegisterInputValidationTypeError.EmptyField) {
                return Result.Error(RegisterInputValidationTypeError.EmptyField)
            } else if (validation == RegisterInputValidationTypeError.EmailNotValid) {
                return Result.Error(RegisterInputValidationTypeError.EmailNotValid)
            } else if (validation == RegisterInputValidationTypeError.PasswordLengthSmall) {
                return Result.Error(RegisterInputValidationTypeError.PasswordLengthSmall)
            } else if (validation == RegisterInputValidationTypeError.PasswordLowerCaseMissing) {
                return Result.Error(RegisterInputValidationTypeError.PasswordLowerCaseMissing)
            } else if (validation == RegisterInputValidationTypeError.PasswordSpecialCharacterMissing) {
                return Result.Error(RegisterInputValidationTypeError.PasswordSpecialCharacterMissing)
            } else if (validation == RegisterInputValidationTypeError.PasswordUpperCaseMissing) {
                return Result.Error(RegisterInputValidationTypeError.PasswordUpperCaseMissing)
            } else if (validation == RegisterInputValidationTypeError.PasswordNumberMissing) {
                return Result.Error(RegisterInputValidationTypeError.PasswordNumberMissing)
            }

        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", "RegisterWithEmail: ${e.message}")
            return Result.Error(RegisterInputValidationTypeError.RegisterAuthenticationError)
        }
        return Result.Error(RegisterInputValidationTypeError.UnKnownRegisterErrorValidation)
    }

    private suspend fun createUserInSupabaseDatabase(
        username: String,
        email: String,
        address: String
    ) {
        val response = supabase.createClient.from("users")
            .insert(
                User(
                    id = (0..100).random(),
                    name = username,
                    email = email,
                    isAdmin = true,
                    address = address
                )
            )
        Log.d("AuthRepositoryImpl", "register: $response")
    }

    private suspend fun saveUserDetailsToDataStore(email: String): Boolean {
        appPreferencesDataSource.saveLoginState(true)
        val userDetails = UserDetailsPreferences.newBuilder()
            .setEmail(email)
            .setName("jamal")
            .setIsAdmin(false)
            .build()
        userPreferencesRepository.updateUserDetails(userDetails).onSuccess {
            Log.d("AuthRepositoryImpl", "add user to dataStore: $it")
            return it
        }.onError {
            Log.d("AuthRepositoryImpl", "add user to dataStore: $it")
            return false
        }
        return false
    }

    override suspend fun logOut(): Result<Boolean, AuthError> {
        try {
            supabase.createClient.auth.signOut()
            appPreferencesDataSource.saveLoginState(false)
            return Result.Success(true)
        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", "logOut: ${e.message}")
            return Result.Error(AuthError.LogOutError)
        }
    }

    override suspend fun isUserAdmin(email: String): Result<Boolean, AuthError> {
        try {
            // Todo(Handle from userProto local Model)
            val checkUserRole = supabase.createClient.from("users")
                .select(columns = Columns.list("email", "isAdmin")) {
                    filter {
                        eq("isAdmin", true)
                        eq("email", email)
                    }
                }
            if (checkUserRole.data != "[]") {
                appPreferencesDataSource.saveRoleState(Role.Admin)
                return Result.Success(true)
            }
        } catch (e: Exception) {
            return Result.Error(AuthError.UserNotFound)
        }
        return Result.Error(AuthError.UserNotFound)
    }

}