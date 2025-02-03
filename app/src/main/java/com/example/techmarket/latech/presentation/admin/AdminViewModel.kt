package com.example.techmarket.latech.presentation.admin


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.admin.AdminRepository
import com.example.techmarket.latech.domain.model.Product
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class AdminViewModel(
    private val supabase: Supabase = Supabase,
    private val adminRepository: AdminRepository
) : ViewModel() {
    
    var state by mutableStateOf(AdminState())
        private set



    fun addProductAdmin(index: Int, name: String, price: Double) {
        state = state.copy(isLoading = true)
        viewModelScope.launch(IO) {
            adminRepository.addProductAdmin(index,name, price).onSuccess{
                if (it){
                    Log.d("addProduct", "Product added successfully")
                    val product =Product(index,name,price,)
                    state = state.copy(products = state.products.toMutableList().apply{this.add(product)} , isLoading = false)
                }else{
                    state = state.copy(error = "error on add product", isLoading = false)
                }
            }
        }
    }

    fun editProductAdmin(index: Int, updatedProduct: Product) {
//        state = state.toMutableList().apply { this[index] = updatedProduct }
    }

    fun deleteProduct(index: Int) {
        state = state.copy(isLoading = true)
        viewModelScope.launch(IO) {
            adminRepository.deleteProductAdmin(index).onSuccess{
                if (it){
//                    state = state.toMutableList().apply { removeAt(index) }
                    Log.d("deleteProduct", "Product deleted successfully")
                }else{
                    Log.d("deleteProduct", "Product deleted failed")
                }
            }
        }
    }

    fun uploadImage(byteArray: ByteArray, mimeType: String) {
        val supabase = Supabase
        val storage = supabase.createClient.storage.from("product")
        val fileExtension = mimeType.substringAfter("/")
        val fileName = "phone_${System.currentTimeMillis()}.$fileExtension"

        viewModelScope.launch {
            try {
                val response = storage.upload(fileName, byteArray)
                Log.d("uploadImage", "Upload successful: $response")
            } catch (e: Exception) {
                Log.d("uploadImage", "Error uploading image: ${e.message}")
            }
        }
    }

    fun signOutAdmin(){
        viewModelScope.launch {
            try {
                adminRepository.signOutAdmin().onSuccess{
                    if (it){
                        state = state.copy(signOut = true)
                        Log.d("signOutAdmin", "signOutAdmin: $it")
                    }
                }
            }catch (e:Exception){
                Log.d("signOutAdmin", "signOutAdmin: ${e.message}")
            }
        }
    }




//    fun acceptOrder(index: Int) {
//        orders = orders.toMutableList().apply { removeAt(index) }
//    }

//    fun refuseOrder(index: Int) {
//        orders = orders.toMutableList().apply { removeAt(index) }
//    }


}