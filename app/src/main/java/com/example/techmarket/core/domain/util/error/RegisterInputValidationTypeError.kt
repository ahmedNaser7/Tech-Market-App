package com.example.techmarket.core.domain.util.error


enum class RegisterInputValidationTypeError : Error {
    EmptyField, // Email or Password is Empty
    EmailNotValid, // Email is Not Valid,
    PasswordUpperCaseMissing, // Password doesn't contain upper case
    PasswordLowerCaseMissing, // Password doesn't contain lower case
    PasswordNumberMissing, // Password doesn't contain number
    PasswordSpecialCharacterMissing, // Password doesn't contain special character
    PasswordLengthSmall, // Password length < 8
    Valid, // All Validation Passed
    RegisterAuthenticationError, // Register Authentication Error
    UnKnownRegisterErrorValidation, // Unknown Error
}