package com.argaed.pruebaedgar.di

import com.argaed.pruebaedgar.httpclient.retrofit.UrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UrlProviderModule {

    @Provides
    fun provideUrlProvider():
            UrlProvider = UrlProvider("http://dummy.restapiexample.com")

}