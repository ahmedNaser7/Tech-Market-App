package com.example.techmarket.core.domain.util.error

enum class NetworkError: Error {
    RequestTimedOut,
    UNAUTHORIZED,
    UNKNOWN_NETWORK_ERROR,
    SERIALIZATION_ERROR,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    NO_INTERNET_CONNECTION,
}
