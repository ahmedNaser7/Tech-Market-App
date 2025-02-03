package com.example.techmarket.latech.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.R
import com.example.techmarket.core.domain.util.onError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.domain.dataSource.home.HomeRepository
import com.example.techmarket.latech.domain.model.Product
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SearchViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _products = MutableStateFlow(listOf<SearchProduct>())
    val products = _searchText
        .debounce(100L)
        .onEach { _isSearching.update { true } }
        .combine(_products) { text, productsList ->
            if (text.isBlank()) {
                productsList
            } else {
                getAllProductsToSearchList()
                delay(300L)
                productsList.filter {
                    it.isMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _products.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun getAllProductsToSearchList() {
        viewModelScope.launch(IO) {
            homeRepository.getAllProducts().onSuccess { products ->
                _products.update {
                    products.map {
                        it.toSearchProduct()
                    }
                }
            }.onError {
                Log.d("SearchViewModel", "getAllProductsToSearchList: $it")
            }
        }
    }
}



data class SearchProduct(
    val image: Int,
    val name: String,
    val price: Double,
) {
    fun isMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            "${name.first()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}


fun Product.toSearchProduct(): SearchProduct {
    return SearchProduct(
        image = imageRes ?: 0,
        name = name,
        price = salary,
    )
}