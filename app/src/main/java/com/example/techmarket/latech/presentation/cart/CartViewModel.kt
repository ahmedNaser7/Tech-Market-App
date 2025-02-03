package com.example.techmarket.latech.presentation.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.domain.dataSource.cart.CartRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    val cartRepository: CartRepository
):ViewModel() {


    private val _state = MutableStateFlow(CartState())
    val state = _state.onStart {
        getProductsToCart()
        getCartDetails()
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(1000),
        initialValue = CartState()
    )


    fun getProductsToCart(){
        Log.d("CartViewModel", "getCartItems: init")
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(IO){
            try {
                cartRepository.getProductsFromCartItems().onSuccess {products ->
                    Log.d("CartViewModel", "getCartItems: ${products.size}")
                    _state.update { cartState ->
                        cartState.copy(
                            products = products,
                            isLoading = false
                        )
                    }

                }
            }catch (e:Exception){
                _state.update { cartState ->
                    cartState.copy(
                        isLoading = false
                    )
                }
                Log.d("CartViewModel", "getCartItems: $e")
            }
        }
    }

    fun getCartDetails(){
        Log.d("CartViewModel", "getCartItems: init")
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(IO){
            try {
                cartRepository.getCartDetails().onSuccess {cart ->
                    _state.update { cartState ->
                        cartState.copy(
                            cartDetails = cart,
                            isLoading = false
                        )
                    }

                }
            }catch (e:Exception){
                _state.update { cartState ->
                    cartState.copy(
                        isLoading = false
                    )
                }
                Log.d("CartViewModel", "getCartDetails: $e")
            }
        }
    }
}