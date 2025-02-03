package com.example.techmarket.latech.presentation.auth.components.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.domain.dataSource.auth.AuthRepository
import com.example.techmarket.latech.presentation.auth.components.state.RegisterState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository
):ViewModel() {
    var registerState by mutableStateOf(RegisterState())
        private set
    fun onUsernameChange(newValue: String) {
        registerState = registerState.copy(usernameInput = newValue)
    }
    fun onEmailChange(newValue: String) {
        registerState = registerState.copy(emailInput = newValue)
    }
    fun onPasswordChange(newValue: String) {
        registerState = registerState.copy(passwordInput = newValue)
    }

    fun register() {
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch(IO) {
            authRepository.register(
                registerState.usernameInput,
                registerState.emailInput,
                registerState.passwordInput
            ).onSuccess{
                registerState = when(it){
                    true ->
                        registerState.copy(
                            isLoading = false,
                            isSuccess = true,
                        )
                    false -> registerState.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = AuthError.RegisterError
                    )
                }
            }
        }
    }
}