package com.example.techmarket.latech.domain.model



import com.example.techmarket.latech.presentation.home.model.AdsUi
import kotlinx.serialization.Serializable



@Serializable
data class Ads(
    val id: Int,
    val productID: Int,
    val discountPercentage: Int,
    val isActive: Boolean,
    val title: String,
    val imageUrl: String,
)

fun Ads.toAdsUi(): AdsUi {
    return AdsUi(
        id = id,
        productID = productID,
        discountPercentage = discountPercentage,
        isActive = isActive,
        title = title,
        imageUrl = imageUrl
    )
}

