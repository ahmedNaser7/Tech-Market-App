package com.example.techmarket.latech.presentation.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmarket.core.domain.util.error.AuthError
import com.example.techmarket.core.domain.util.onError
import com.example.techmarket.core.domain.util.onSuccess
import com.example.techmarket.latech.domain.dataSource.account.ProfileRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state
        .onStart {
            getAccountInfo()
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProfileState()
        )


    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.OnLogout -> {
                logOut()
            }
        }
    }

    fun getAccountInfo() {
        viewModelScope.launch(IO) {
            profileRepository.getAccountInfo()
                .onSuccess { profile ->
                    Log.d("ProfileViewModel", "getAccountInfo: ${profile.userName}")
                    _state.update {
                        it.copy(
                            profile = profile,
                            isLoading = false,
                            error = null
                        )
                    }
                }.onError {error ->
                     _state.update {
                          it.copy(
                               error = error.toString()
                          )
                     }
                 }
        }
    }

    fun logOut() {
        viewModelScope.launch(IO) {
            profileRepository.logOut().onSuccess {
                _state.update {
                    it.copy(
                        isLogOut = true
                    )
                }
            }.onError {
                _state.update {
                    it.copy(
                        isLogOut = false,
                        error = AuthError.UserNotFound.name
                    )
                }
            }
        }
    }
}