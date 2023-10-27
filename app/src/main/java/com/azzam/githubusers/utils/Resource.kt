package com.azzam.githubusers.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null, val errorCode: Int? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String?, data: T? = null, errorCode: Int?): Resource<T>(data, message, errorCode)
}