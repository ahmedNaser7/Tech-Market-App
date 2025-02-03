package com.example.techmarket.core.presentation.util

import android.content.Context
import com.example.techmarket.R
import com.example.techmarket.core.domain.util.error.NetworkError

fun NetworkError.toString(context: Context):String{
    return when(this){
        NetworkError.RequestTimedOut -> context.getString(R.string.request_timed_out)
        NetworkError.UNAUTHORIZED -> context.getString(R.string.unauthorized)
        NetworkError.UNKNOWN_NETWORK_ERROR -> context.getString(R.string.unknown_network_error)
        NetworkError.SERIALIZATION_ERROR -> context.getString(R.string.serialization_error)
        NetworkError.TOO_MANY_REQUESTS -> context.getString(R.string.too_many_requests)
        NetworkError.SERVER_ERROR -> context.getString(R.string.server_error)
        NetworkError.NO_INTERNET_CONNECTION -> context.getString(R.string.no_internet_connection)
    }
}