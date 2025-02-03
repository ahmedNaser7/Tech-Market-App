package com.example.techmarket.latech.presentation.auth.components.state

import com.example.techmarket.core.domain.util.error.AuthError

data class RegisterState(
    val usernameInput: String="",
    val emailInput: String = "",
    val passwordInput: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: AuthError? = null
)
