package com.argaed.pruebaedgar.httpclient.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/* */
class RetrofitBuilder @Inject constructor(
    private val urlBase: String
)  {

    /* */
    private val bodyInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /* */
    private val timeOut: Long = 10000L

    
    fun build(): Retrofit =
        Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    
    private fun buildHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS).apply {
                addInterceptor(bodyInterceptor)
            }
            .build()

}