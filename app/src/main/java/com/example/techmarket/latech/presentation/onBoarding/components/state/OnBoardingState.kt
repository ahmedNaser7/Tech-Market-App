package com.example.techmarket.latech.presentation.onBoarding.components.state


data class OnBoardingState(
    val isLoading: Boolean = true,
    val networkStatue:Boolean = false,
    val isLogged:Boolean = false,
    val isAdmin:Boolean = false
)
