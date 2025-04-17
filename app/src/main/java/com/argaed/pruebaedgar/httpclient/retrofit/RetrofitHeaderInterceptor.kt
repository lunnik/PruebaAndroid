package com.argaed.pruebaedgar.httpclient.retrofit

interface RetrofitHeaderInterceptor {

    fun getAuthorizationType() : String

    fun getAuthorizationValue() : String?

}
