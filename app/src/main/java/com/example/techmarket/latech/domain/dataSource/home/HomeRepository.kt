package com.example.techmarket.latech.domain.dataSource.home

import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.HomeError
import com.example.techmarket.latech.domain.model.Ads
import com.example.techmarket.latech.domain.model.Product


interface HomeRepository {
    suspend fun getAllAds():Result<List<Ads>,HomeError>
    suspend fun getAllProducts():Result<List<Product>,HomeError>
}