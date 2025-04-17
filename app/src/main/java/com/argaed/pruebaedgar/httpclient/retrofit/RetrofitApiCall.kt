package com.argaed.pruebaedgar.httpclient.retrofit

import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response

@Throws(HttpException::class, Exception::class)
suspend fun <T> retrofitApiCall(apiCall: suspend () -> Response<T>): T {
    val response = apiCall()
    return if (response.isSuccessful)
        response.body()!!
    else throw HttpException(response)
}

@Throws(HttpException::class, Exception::class)
suspend fun notContentRetrofitApiCall(apiCall: suspend () -> Response<Unit>) {
    val response = apiCall()
    if (!response.isSuccessful)
        throw HttpException(response)
}

fun HttpException.errorMessage(): String = try {
    val jObjError = JSONObject(response()?.errorBody()?.string()!!)
    jObjError.getString("message") ?: message()
} catch (e: Exception) {
    message()
}

fun HttpException.errorDescription(): String = try {
    val jObjError = JSONObject(response()?.errorBody()?.string()!!)
    jObjError.getString("description") ?: message()
} catch (e: Exception) {
    message()
}

fun HttpException.errorCode(): Int = try {
    val jObjError = JSONObject(response()?.errorBody()?.string()!!)
    jObjError.getInt("code")
} catch (e: Exception) {
    code()
}
