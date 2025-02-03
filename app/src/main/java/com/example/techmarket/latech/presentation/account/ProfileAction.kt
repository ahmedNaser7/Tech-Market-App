package com.example.techmarket.latech.presentation.account

sealed class ProfileAction {
    data object OnLogout:ProfileAction()
}