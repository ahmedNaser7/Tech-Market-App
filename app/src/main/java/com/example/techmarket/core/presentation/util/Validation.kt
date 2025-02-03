package com.example.techmarket.core.presentation.util


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