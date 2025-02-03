package com.example.techmarket.latech.data.dataSource.product

import android.util.Log
import com.example.techmarket.core.domain.util.Result
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.product.ProductRepository
import com.example.techmarket.latech.domain.model.Cart
import com.example.techmarket.latech.domain.model.CartItem
import com.example.techmarket.latech.domain.model.Product
import com.example.techmarket.latech.domain.model.User
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlin.random.Random

class ProductRepositoryImpl(
    val supabase: Supabase
) : ProductRepository {
    override suspend fun addProductToCart(product: Product): Result<Boolean, AuthError> {
        try {
            val CartItemsTable = supabase.createClient.from("cartItems")
            val cartItemOfProduct = CartItemsTable.select {
                filter {
                    eq("productID", product.id)
                }
            }
            if (cartItemOfProduct.data == "[]") {
                val cartId = createCartForProduct(product)
                createCartItemForProduct(product, cartId)
                if (cartId > 0)
                    return Result.Success(true)
                else
                    return Result.Error(AuthError.LoginError)
            } else {
                val quantity = cartItemOfProduct.decodeSingle<CartItem>().quantity
                val updateCartItem = CartItemsTable.update({
                    set("quantity", quantity + 1)
                }) {
                    filter {
                        eq("productID", product.id)
                    }
                }
                if (updateCartItem.data != "[]") {
                    return Result.Success(true)
                }
                return Result.Error(AuthError.LoginError)
            }

        } catch (e: Exception) {
            Log.d("ProductRepositoryImpl", "error: $e")
            return Result.Error(AuthError.LoginError)
        }
    }

    private suspend fun createCartItemForProduct(product: Product, cartId: Int) {
        val cartItem = CartItem(
            id = Random.nextInt(5, 100),
            cartID = cartId,
            productID = product.id,
            quantity = 1,
        )
        supabase.createClient.from("cartItems").insert(cartItem)
    }

    private suspend fun createCartForProduct(product: Product): Int {
        val UserTable = supabase.createClient.from("users")
        val CartTable = supabase.createClient.from("cart")
        val currentUserEmail = supabase.createClient.auth.currentSessionOrNull()?.user?.email

        val userId = UserTable.select {
            filter {
                eq("email", currentUserEmail.toString())
            }
        }.decodeSingle<User>().id
        val checkUserCart = CartTable.select {
            filter {
                eq("userID", userId!!)
            }
        }.decodeSingle<Cart>()
        return if (checkUserCart.id > 0) {
            CartTable.update({
                set("totalItems", checkUserCart.totalItems + 1)
                set("totalPrice", (checkUserCart.totalPrice + product.salary).toFloat())
            }) {
                filter {
                    eq("id", checkUserCart.id)
                }
            }
            checkUserCart.id
        } else {
            val newCart = Cart(
                id = Random.nextInt(2, 100),
                userID = userId!!,
                totalItems = 1,
                totalPrice = product.salary
            )
            CartTable.insert(newCart)
            newCart.id
        }


    }
}

