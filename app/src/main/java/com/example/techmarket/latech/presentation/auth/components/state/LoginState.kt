package com.example.techmarket.latech.presentation.auth.components.state

import com.example.techmarket.core.domain.util.error.LoginInputValidationTypeError

data class LoginState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isLoading: Boolean = false,
    val isValidate: Boolean = false,
    val isSuccess: Boolean = false,
    val error: LoginInputValidationTypeError? = null,
    val isAdmin:Boolean = false
)
