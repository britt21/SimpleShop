package com.example.common

sealed class NetworkCall<T> (val data : T? = null, val message : String? = null){

    class Success<T>(data: T, message: String? = null) : NetworkCall<T>(data, message)
    class Error<T>(message: String, data: T? = null) : NetworkCall<T>(data, message)
    class Loading<T>() : NetworkCall<T>()
}