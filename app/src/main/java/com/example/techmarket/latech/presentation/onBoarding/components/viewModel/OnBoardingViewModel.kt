package com.example.techmarket.latech.presentation.onBoarding.components.viewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.data.datasource.NetworkConnectivityObserverRepositoryImpl
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.core.domain.datasource.ConnectivityObserverRepository
import com.example.techmarket.latech.presentation.onBoarding.components.state.OnBoardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class OnBoardingViewModel(
    context: Context,
    private val appPreferencesDataSource: AppPreferencesDataSource
) : ViewModel() {
    private val networkConnectivityObserver = NetworkConnectivityObserverRepositoryImpl(context)
    private val _state = MutableStateFlow(OnBoardingState())
    val state = _state
        .onStart {
            isUserLoggedIn()
            checkStatuesOfNetwork()
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = OnBoardingState()
        )


    private fun checkStatuesOfNetwork() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            networkConnectivityObserver.observe().collect { statue ->
                when (statue) {
                    ConnectivityObserverRepository.Status.Available -> {
                        _state.update { state ->
                            state.copy(isLoading = false, networkStatue = true)
                        }
                    }

                    ConnectivityObserverRepository.Status.Unavailable -> {
                        _state.update { state ->
                            state.copy(isLoading = false, networkStatue = false)
                        }
                    }

                    ConnectivityObserverRepository.Status.Losing -> {
                        _state.update { state ->
                            state.copy(isLoading = false, networkStatue = false)
                        }
                    }

                    ConnectivityObserverRepository.Status.Lost -> {
                        _state.update { state ->
                            state.copy(isLoading = false, networkStatue = false)
                        }
                    }

                }

            }
        }
    }

    private fun isUserLoggedIn() {
        _state.update { state ->
            state.copy(isLoading = true)
        }
        viewModelScope.launch {
            appPreferencesDataSource.isUserLoggedIn.collect {
                if (it) {
                    _state.update { state ->
                        state.copy(isLoading = false, isLogged = true)
                    }
                } else {
                    _state.update { state ->
                        state.copy(isLoading = false, isLogged = false)
                    }
                }
            }
        }
    }

//    fun isUserAdmin(){
//        viewModelScope.launch {
//            appPreferencesDataSource.userRole.collect {
//                when (it) {
//                    Role.Admin -> state = state.copy(isLoading = false, isAdmin = true)
//                    Role.Customer -> state = state.copy(isLoading = false, isAdmin =false)
//                }
//            }
//        }
//    }
}