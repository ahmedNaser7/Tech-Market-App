package com.example.techmarket.latech.presentation.account

import com.example.techmarket.latech.domain.model.Profile
import com.example.techmarket.latech.domain.model.User

data class ProfileState(
    val profile: Profile?=null,
    val isLoading:Boolean=false,
    val error:String?=null,
    val isLogOut:Boolean=false
)
