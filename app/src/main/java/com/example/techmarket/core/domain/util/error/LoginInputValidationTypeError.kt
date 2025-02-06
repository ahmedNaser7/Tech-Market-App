package com.example.techmarket.core.domain.util.error

enum class LoginInputValidationTypeError : Error {
    EmptyField,
    NoEmail,
    Valid,
}