package com.example.techmarket.core.domain.use_case

import com.example.techmarket.core.domain.util.error.RegisterInputValidationTypeError
import com.example.techmarket.core.presentation.util.containsDigit
import com.example.techmarket.core.presentation.util.containsLowerCase
import com.example.techmarket.core.presentation.util.containsSpecialCharacter
import com.example.techmarket.core.presentation.util.containsUpperCase
import com.example.techmarket.core.presentation.util.isPasswordLengthValid
import com.example.techmarket.core.presentation.util.isValidEmail
import com.example.techmarket.core.presentation.util.isValidPassword

class ValidateRegisterInputCase {

    operator fun invoke(email: String, password: String): RegisterInputValidationTypeError {
        if (email.isEmpty() && password.isEmpty()) {
            return RegisterInputValidationTypeError.EmptyField
        } else if (!email.isValidEmail()) {
            return RegisterInputValidationTypeError.EmailNotValid
        } else if (!password.isPasswordLengthValid()) {
            return RegisterInputValidationTypeError.PasswordLengthSmall
        } else if (!password.containsLowerCase()) {
            return RegisterInputValidationTypeError.PasswordLowerCaseMissing
        } else if (!password.containsUpperCase()) {
            return RegisterInputValidationTypeError.PasswordUpperCaseMissing
        } else if (!password.containsDigit()) {
            return RegisterInputValidationTypeError.PasswordNumberMissing
        } else if (!password.containsSpecialCharacter()) {
            return RegisterInputValidationTypeError.PasswordSpecialCharacterMissing
        } else if (email.isValidEmail() && password.isValidPassword())
        // edit the part of email & password is match
            return RegisterInputValidationTypeError.Valid
        else {
            return RegisterInputValidationTypeError.UnKnownRegisterErrorValidation
        }
    }
}