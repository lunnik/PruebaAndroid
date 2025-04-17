package com.argaed.pruebaedgar.httpclient

import com.argaed.pruebaedgar.httpclient.retrofit.UrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HttpClientModule {

    private val timeOut: Long = 10000L

    @Provides
    fun provideBaseUrl(urlProvider: UrlProvider): String = urlProvider.baseUrl

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(getHeaderInterceptor())
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .build()
    }

    private fun getHeaderInterceptor() = Interceptor {
        val newRequest = it.request().newBuilder()
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/115.0.0.0 Safari/537.36")
            .header("Accept", "application/json, text/plain, */*")
            .header("Accept-Language", "es-ES,es;q=0.9")
            .build()
        it.proceed(newRequest)
    }
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        url: String,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(okHttpClient)
            .build()

}