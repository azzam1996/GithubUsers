package com.azzam.githubusers.data.api

import android.util.Log
import com.azzam.githubusers.utils.NO_INTERNET_ERROR_CODE
import com.azzam.githubusers.utils.Resource
import retrofit2.Response
import java.lang.Exception
import timber.log.Timber


private fun <T> parseResponse(response: Response<T>): Resource<T?> {
    return if (response.isSuccessful) {
        Resource.Success(response.body())
    } else {
        val errorMessage = response.errorBody()?.string()
        Resource.Error(
            errorCode = response.code(),
            message = errorMessage,
        )
    }
}

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T?> {
    return try {
        parseResponse(apiCall.invoke())
    } catch (e: NoConnectivityException) {
        Timber.tag("GithubUsers : ").e(Log.getStackTraceString(e))
        Resource.Error(message = e.message, errorCode = NO_INTERNET_ERROR_CODE)
    } catch (e: Exception) {
        Timber.tag("GithubUsers : ").e(Log.getStackTraceString(e))
        Resource.Error(message = e.message, errorCode = -1)
    }
}