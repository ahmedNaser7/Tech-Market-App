package com.example.techmarket.core.domain.use_case

import com.example.techmarket.core.domain.util.error.LoginInputValidationTypeError
import com.example.techmarket.core.presentation.util.isValidEmail


class ValidateLoginInputCase {

    operator fun invoke(email: String, password: String): LoginInputValidationTypeError {
        return if (email.isEmpty() && password.isEmpty())
            LoginInputValidationTypeError.EmptyField
        else if (email.isEmpty())
            LoginInputValidationTypeError.EmailFieldEmpty
        else if (password.isEmpty())
            LoginInputValidationTypeError.PasswordFieldEmpty
        else if (!email.isValidEmail() && password.isNotEmpty())
            LoginInputValidationTypeError.EmailIsNotValid
        else if (email.isValidEmail() && password.isNotEmpty())
            LoginInputValidationTypeError.Valid
        else
            LoginInputValidationTypeError.EmailNotMatchWithPassword
    }
}