package com.example.techmarket.latech.domain.model

import com.example.techmarket.latech.domain.model.user.UserDetailsPreferences
import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int?=null,
    val name: String?,
    val email: String?,
    val address: String?=null,
    val isAdmin: Boolean?,
)


fun User.toUserDetailsPreferences(): UserDetailsPreferences {
    return UserDetailsPreferences.newBuilder()
        .setId(id.toString())
        .setName(name)
        .setEmail(email)
        .setAddress(address)
        .build()
}
object Role{
    val Customer = "Customer"
    val Admin = "Admin"
}


