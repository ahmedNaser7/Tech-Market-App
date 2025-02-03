package com.example.techmarket.latech.data.dataSource.admin

import android.util.Log
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.admin.AdminRepository
import com.example.techmarket.latech.domain.model.Product
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AdminRepositoryImpl(
    private val supabase: Supabase ,
    private val appPreferencesDataSource: AppPreferencesDataSource
):AdminRepository {
    override suspend fun addProductAdmin(index: Int, name: String, price: Double): Result<Boolean, AuthError> {
         try {
             supabase.createClient.from("products").insert(Product(
                 id = (index),
                 name = name,
                 salary = price,
                 category = "tech",
                 colors = listOf("red"),
                 brand = "samsung",
                 stock = true,
                 image = ""
             ))
             return Result.Success(true)
         }catch (e:Exception){
             Log.d("AdminProductsImpl", "Error on addProduct: $e")
             return Result.Error(AuthError.UserNotFound)
         }
    }

    override suspend fun deleteProductAdmin(productId: Int): Result<Boolean, AuthError>{
        try {
            supabase.createClient.from("products").delete{
                filter{
                    eq("id",productId)
                }
            }
            return Result.Success(true)
        }catch (e:Exception){
            Log.d("AdminProductsImpl", "Error on deleteProduct: $e")
            return Result.Error(AuthError.UserNotFound)
        }
    }

    override suspend fun signOutAdmin(): Result<Boolean, AuthError> {
        try {
            supabase.createClient.auth.signOut()
            appPreferencesDataSource.saveLoginState(false)
            appPreferencesDataSource.saveRoleState(null)
            return Result.Success(true)
        }catch (e:Exception){
            Log.d("AdminProductsImpl", "Error on signOut: $e")
            return Result.Error(AuthError.UserNotFound)
        }
    }
}