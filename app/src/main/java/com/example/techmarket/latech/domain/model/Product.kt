package com.example.techmarket.latech.domain.model

import com.example.techmarket.R
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val salary: Double,
    val image:String?=null,
    val category: String?=null,
    val stock:Boolean?=null,
    val brand:String?=null,
    val colors:List<String>?=null,
    val capacity:List<String>?=null,
    val imageRes:Int?= R.drawable.product,
)
