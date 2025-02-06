package com.example.techmarket.core.domain.util.error


enum class RegisterInputValidationTypeError : Error {
    EmptyField,
    NoEmail,
    PasswordNotMatch,
    PasswordUpperCaseMissing,
    PasswordLowerCaseMissing,
    PasswordNumberMissing,
    PasswordSpecialCharacterMissing,
    PasswordLengthSmall,
    Valid,
}