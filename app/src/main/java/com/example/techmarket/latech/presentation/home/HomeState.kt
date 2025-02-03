package com.example.techmarket.latech.presentation.home

import com.example.techmarket.latech.domain.model.Product
import com.example.techmarket.latech.presentation.home.model.AdsUi

data class HomeState (
    val ads:List<AdsUi> = emptyList(),
    val products:List<Product> = emptyList(),
    val isLoading:Boolean = false,
    val error:String? = null,
    val isSuccess:Boolean = false
)

