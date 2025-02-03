package com.example.techmarket.latech.presentation.product

import com.example.techmarket.core.domain.util.error.Error


sealed interface ProductEvent{
     data class Error(val error: String):ProductEvent
}