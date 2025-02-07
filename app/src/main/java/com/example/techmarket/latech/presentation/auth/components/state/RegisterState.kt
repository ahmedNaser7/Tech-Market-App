package com.example.techmarket.latech.presentation.auth.components.state

import com.example.techmarket.core.domain.util.error.RegisterInputValidationTypeError

data class RegisterState(
    val usernameInput: String="",
    val emailInput: String = "",
    val passwordInput: String = "",
    val addressInput: String = "",
    val isValid: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: RegisterInputValidationTypeError? = null
)
