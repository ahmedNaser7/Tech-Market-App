package com.example.techmarket.latech.presentation.auth.components.event

data class LoginEvent(
    val onLoginAction: () -> Unit,
)
