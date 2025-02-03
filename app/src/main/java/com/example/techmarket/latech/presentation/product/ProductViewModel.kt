package com.example.techmarket.latech.presentation.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.domain.dataSource.product.ProductRepository
import com.example.techmarket.latech.domain.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
):ViewModel() {

//    var state by mutableStateOf(ProductState())
//        private set

    private val _state = MutableStateFlow(ProductState())
    val state get() = _state


    private val _events = Channel<ProductEvent?>()
    val events = _events.receiveAsFlow()


    fun onAction(action: ProductAction){
        when(action){
            is ProductAction.AddToCart -> addProductToCart(action.product)
        }
    }


    private fun addProductToCart(product: Product){
        Log.d("ProductViewModel", "init ")
        _state.update {
            it.copy(
                isLoading = true,
                selectedProduct = product
            )
        }
        viewModelScope.launch {
            try {
                productRepository.addProductToCart(product).onSuccess{
                    if (it){
                        Log.d("ProductViewModel", "Product added to cart condition : $it")
                        _state.update {  productState ->
                            productState.copy(
                                selectedProduct = productState.selectedProduct,
                                isAddedToCart = true,
                                isLoading = false,
                                error = null,
                            )
                        }
                    }
                }
            }catch (e:Exception){
                Log.d("ProductViewModel", "Product added to cart condition : ${e.message}")
                _events.send(ProductEvent.Error(error = e.message?:"Error"))
            }
        }
    }
}