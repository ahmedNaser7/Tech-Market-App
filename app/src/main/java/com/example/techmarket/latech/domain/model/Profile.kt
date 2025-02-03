package com.example.techmarket.latech.domain.model

import com.example.techmarket.latech.domain.model.user.UserDetailsPreferences

data class Profile(
    val userName: String,
    val email:String,
    val accountType:String
)

fun UserDetailsPreferences.toProfile():Profile{
    return Profile(
        userName = name,
        email = email,
        accountType = if(isAdmin) "Admin" else "Customer"
    )
}
