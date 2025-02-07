package com.example.techmarket.core.presentation.util

import com.example.techmarket.core.domain.util.error.RegisterInputValidationTypeError


val emailRegex = Regex(
    "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
)

val passwordRegex = Regex(
    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
)

fun String.isValidEmail(): Boolean{
    return this.matches(emailRegex)
}

fun String.isValidPassword(): Boolean{
    return this.matches(passwordRegex)
}


fun String.containsLowerCase(): Boolean {
    return this.any { it.isLowerCase() }
}

fun String.containsUpperCase(): Boolean {
    return this.any { it.isUpperCase() }
}

fun String.containsDigit(): Boolean {
    return this.any { it.isDigit() }
}

fun String.containsSpecialCharacter(): Boolean {
    val specialChars = setOf('@', '$', '!', '%', '*', '?', '&')
    return this.any { it in specialChars }
}

fun String.isPasswordLengthValid(): Boolean {
    return this.length >= 8
}

