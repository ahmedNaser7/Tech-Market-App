package com.example.techmarket.latech.presentation.auth.components.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.domain.util.onError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.domain.dataSource.auth.AuthRepository
import com.example.techmarket.latech.presentation.auth.components.state.LoginState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    var loginState by mutableStateOf(LoginState())
        private set

    fun onEmailChange(newValue: String) {
        loginState = loginState.copy(emailInput = newValue)

    }

    fun onPasswordChange(newValue: String) {
        loginState = loginState.copy(passwordInput = newValue)

    }

    fun loginWithEmail() {
        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch(IO) {
            authRepository.loginWithEmail(
                loginState.emailInput,
                loginState.passwordInput
            ).onSuccess {
                loginState = loginState.copy(isSuccess = true, isValidate = true, isLoading = false)
            }.onError { loginError ->
                loginState = loginState.copy(
                    isSuccess = false,
                    isValidate = false,
                    isLoading = false,
                    error = loginError
                )
            }
        }
    }

    fun isUserAdmin() {
        viewModelScope.launch(IO) {
            authRepository.isUserAdmin(loginState.emailInput).onSuccess {
                loginState = loginState.copy(isAdmin = it)
            }
        }
    }

    // Todo(fix the problem of validation place )


}