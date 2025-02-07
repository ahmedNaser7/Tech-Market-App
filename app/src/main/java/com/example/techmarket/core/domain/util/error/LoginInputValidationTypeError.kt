package com.example.techmarket.core.domain.util.error

enum class LoginInputValidationTypeError : Error {
    EmptyField,
    EmailIsNotValid,
    EmailNotMatchWithPassword,
    Valid,
    UnknownLoginError,
    EmailFieldEmpty,
    PasswordFieldEmpty,
}