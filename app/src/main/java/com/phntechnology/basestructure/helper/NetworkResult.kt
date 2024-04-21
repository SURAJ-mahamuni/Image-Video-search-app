package com.phntechnology.basestructure.helper


sealed class NetworkResult<T>(val data: T? = null, val message: Int? = null) {
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(message: Int?, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()
}
