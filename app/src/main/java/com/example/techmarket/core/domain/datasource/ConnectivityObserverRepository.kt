package com.example.techmarket.core.domain.datasource

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserverRepository {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}

