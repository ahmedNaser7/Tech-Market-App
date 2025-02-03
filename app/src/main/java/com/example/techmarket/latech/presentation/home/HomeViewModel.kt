package com.example.techmarket.latech.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.domain.util.onError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.data.dataSource.home.HomeRepositoryImpl
import com.example.techmarket.latech.domain.dataSource.home.HomeRepository
import com.example.techmarket.latech.domain.model.toAdsUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository = HomeRepositoryImpl()
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart {
            getAllAds()
            getAllProduct()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeState()
        )


    fun getAllAds() {
        viewModelScope.launch {
            repository.getAllAds().onSuccess { ads ->
                _state.update {
                    it.copy(ads = ads.map { it.toAdsUi() }, isSuccess = true)
                }
            }.onError { error ->
                _state.update {
                    it.copy(error = error.toString(), isSuccess = false)
                }
            }
        }
    }

    fun getAllProduct() {
        viewModelScope.launch {
            repository.getAllProducts().onSuccess { products ->
                _state.update {
                    it.copy(products = products, isSuccess = true)
                }
            }.onError { error ->
                _state.update {
                    it.copy(error = error.toString(), isSuccess = false)
                }
            }
        }
    }


}
