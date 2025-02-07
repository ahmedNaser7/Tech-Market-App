package com.example.techmarket.core.presentation.util

import android.content.Context
import com.example.techmarket.core.domain.util.error.LoginInputValidationTypeError
import com.example.techmarket.core.domain.util.error.RegisterInputValidationTypeError


fun LoginInputValidationTypeError.toString(context: Context): String {
    return when (this) {
        LoginInputValidationTypeError.EmptyField -> "Empty Field"
        LoginInputValidationTypeError.EmailFieldEmpty -> "Email Field Is Empty"
        LoginInputValidationTypeError.PasswordFieldEmpty -> "Password Field Is Empty"
        LoginInputValidationTypeError.EmailNotMatchWithPassword -> "Email Not Match With Password"
        LoginInputValidationTypeError.Valid -> " Login Succeeded "
        LoginInputValidationTypeError.UnknownLoginError -> "Unknown Error"
        LoginInputValidationTypeError.EmailIsNotValid -> "Email Is Not Valid"
    }
}


fun RegisterInputValidationTypeError.toString(context: Context): String {
    return when (this) {
        RegisterInputValidationTypeError.EmptyField -> "Empty Field"
        RegisterInputValidationTypeError.EmailNotValid -> "Email Not Valid"
        RegisterInputValidationTypeError.PasswordUpperCaseMissing -> "Password Must Contain Upper Case"
        RegisterInputValidationTypeError.PasswordLowerCaseMissing -> "Password Must Contain Lower Case"
        RegisterInputValidationTypeError.PasswordNumberMissing -> "Password Must Contain Number"
        RegisterInputValidationTypeError.PasswordSpecialCharacterMissing -> "Password Must Contain Special Character"
        RegisterInputValidationTypeError.PasswordLengthSmall -> "Password Must Contain At Least 8 Characters"
        RegisterInputValidationTypeError.Valid -> " Registration Succeeded "
        RegisterInputValidationTypeError.RegisterAuthenticationError -> "Server On Registration Error "
        RegisterInputValidationTypeError.UnKnownRegisterErrorValidation -> "Unknown Error"
    }

}


