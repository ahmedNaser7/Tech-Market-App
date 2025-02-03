package com.example.techmarket.core.domain.util.error

enum class AuthError: Error {
    UserNotFound,
    LoginError,
    RegisterError,
    LogOutError,
}