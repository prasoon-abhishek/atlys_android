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
    @Qualifiers.ApiServiceMovies
    fun provideApiServiceMovies(): ApiService {
        return RetrofitClient.getRetrofitInstance(RetrofitClient.BASE_URL_MOVIE)
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    @Qualifiers.ApiServiceImages
    fun provideApiServiceImages(): ApiService {
        return RetrofitClient.getRetrofitInstance(RetrofitClient.BASE_URL_IMAGE)
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        @Qualifiers.ApiServiceMovies apiServiceMovie: ApiService,
        @Qualifiers.ApiServiceImages apiServiceImage: ApiService,
    ) = MoviesRepository(apiServiceMovie, apiServiceImage)
}