package com.prasoon.atlysandroid.di

import com.prasoon.atlysandroid.network.ApiService
import com.prasoon.atlysandroid.network.MoviesRepository
import com.prasoon.atlysandroid.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApiServiceMovies(): ApiService {
        return RetrofitClient.getRetrofitInstance()
            .create(ApiService::class.java)
    }
}