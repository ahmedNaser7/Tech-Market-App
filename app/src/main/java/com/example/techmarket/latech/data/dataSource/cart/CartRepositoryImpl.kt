package com.example.techmarket.latech.data.dataSource.cart

import android.util.Log
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.cart.CartRepository
import com.example.techmarket.latech.domain.model.Cart
import com.example.techmarket.latech.domain.model.CartItem
import com.example.techmarket.latech.domain.model.Product
import com.example.techmarket.latech.domain.model.User
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from


class CartRepositoryImpl(
    private val supabase: Supabase
) : CartRepository {
    override suspend fun getProductsFromCartItems(): Result<List<Product>, AuthError> {
        try {
            val cartId = getCartDetailsByUserId()?.id ?: 0
            Log.d("CartRepositoryImpl", "cart Id: $cartId")
            val cartItems = supabase.createClient.from("cartItems").select {
                filter {
                    eq("cartID", cartId)
                }
            }.decodeList<CartItem>()
            val products = mutableListOf<Product>()
            supabase.createClient.from("products").select().decodeList<Product>()
            for (i in cartItems.indices) {
                val product = supabase.createClient.from("products").select {
                    filter {
                        eq("id", cartItems.map { it.productID }[i])
                    }
                }.decodeSingle<Product>()
                products.add(product)
            }
            return Result.Success(products)
        } catch (e: Exception) {
            Log.d("CartRepositoryImpl", "ProductsFromCartItems: $e")
            return Result.Error(AuthError.UserNotFound)
        }
    }

    override suspend fun getCartDetails(): Result<Cart, AuthError>{
        try {
            val cartDetails = getCartDetailsByUserId()
            return Result.Success(cartDetails!!)
        } catch (e: Exception) {
            Log.d("CartRepositoryImpl", "getCartDetails: $e")
            return Result.Error(AuthError.UserNotFound)
        }
    }


    private suspend fun getCartDetailsByUserId(): Cart? {
        try {
            val currentUserEmail = supabase.createClient.auth.currentSessionOrNull()?.user?.email
            val userId = supabase.createClient.from("users").select {
                filter {
                    eq("email", currentUserEmail.toString())
                }
            }.decodeSingle<User>().id
            Log.d("CartRepositoryImpl", "userId: $userId")
            val cartDetails = supabase.createClient.from("cart").select {
                filter {
                    eq("userID", userId!!)
                }
            }.decodeSingle<Cart>()
            Log.d("CartRepositoryImpl", "CartDetailsByUserId: $cartDetails")
            return cartDetails
        } catch (e: Exception) {
            Log.d("CartRepositoryImpl", "CartDetailsByUserId: $e")
        }
        return null
    }
}