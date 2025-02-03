package com.example.techmarket.core.presentation.util

import android.content.Context
import com.example.techmarket.R
import com.example.techmarket.core.domain.util.error.AuthError

fun AuthError.toString(context: Context):String{
    return when(this){
        AuthError.UserNotFound -> context.getString(R.string.user_not_found)
        AuthError.LoginError -> context.getString(R.string.login_error)
        AuthError.RegisterError -> context.getString(R.string.register_error)
        AuthError.LogOutError -> context.getString(R.string.logOut_error)
    }
}

