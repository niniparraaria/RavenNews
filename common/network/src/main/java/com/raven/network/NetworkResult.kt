package com.raven.network

sealed class NetworkResult<T>(val data:T? = null, val status: NetworkStatus, val errorMessage:String? = null ){
    class Loading<T>: NetworkResult<T>(status = NetworkStatus.LOADING)
    class Success<T>(data: T?): NetworkResult<T>(status = NetworkStatus.SUCCESS, data = data)
    class Error<T>(message:String): NetworkResult<T>(status = NetworkStatus.ERROR, errorMessage = message)
}

enum class NetworkStatus{
    LOADING, SUCCESS, ERROR
}
