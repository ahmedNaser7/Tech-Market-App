package com.example.techmarket.latech.data.dataSource.home

import android.util.Log
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.error.HomeError
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.home.HomeRepository
import com.example.techmarket.latech.domain.model.Ads
import com.example.techmarket.latech.domain.model.Product
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.annotations.Async.Execute


class HomeRepositoryImpl(
    val supabase: Supabase = Supabase
):HomeRepository{
    override suspend fun getAllAds(): Result<List<Ads>, HomeError> {
        try {
            val adsList = supabase.createClient.from("ads").select().decodeList<Ads>()
            Log.d("HomeRepositoryImpl", "return: $adsList")
            return Result.Success(adsList)
        }catch (e:Exception){
            Log.d("HomeRepositoryImpl", "Error on getAllAds: $e")
            return Result.Error(HomeError.AdsNotFound)
        }
    }

    override suspend fun getAllProducts(): Result<List<Product>, HomeError> {
        try {
            val products = supabase.createClient.from("products").select().decodeList<Product>()
            return Result.Success(products)
        }catch (e:Exception){
            Log.d("HomeRepositoryImpl", "Error on getAllProducts: $e")
            return Result.Error(HomeError.ProductsNotFound)
        }
    }

}