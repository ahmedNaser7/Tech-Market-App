package com.example.techmarket.latech.presentation.connection.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ConnectionViewModel(
    private val appPreferencesDataSource: AppPreferencesDataSource
):ViewModel(){
    var connectionState by mutableStateOf(ConnectionState())
        private set
    private fun checkIsLogin(){
        connectionState = connectionState.copy(isLoading= true)
        viewModelScope.launch {
            val isLogged = appPreferencesDataSource.isUserLoggedIn.first()
            connectionState = if (isLogged){
                connectionState.copy(isLogged = false, isLoading = false)
            }
            else{
                connectionState.copy(isLogged = false, isLoading = false)
            }
        }

    }

    init {
        checkIsLogin()
    }
}