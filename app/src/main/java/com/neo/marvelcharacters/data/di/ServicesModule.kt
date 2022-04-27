package com.neo.marvelcharacters.data.di

import com.neo.marvelcharacters.data.remote.service.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Singleton
    @Provides
    fun providesMarvelService(retrofit: Retrofit): MarvelService {
        return retrofit.create(MarvelService::class.java)
    }
}