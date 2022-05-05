package com.neo.marvelcharacters.data.di

import com.neo.marvelcharacters.core.MarvelApi
import com.neo.marvelcharacters.data.remote.interceptor.MarvelApiCredential
import com.neo.marvelcharacters.data.remote.service.MarvelService
import com.neo.marvelcharacters.data.repository.MarvelRepositoryImpl
import com.neo.marvelcharacters.data.source.MarvelPagingSource
import com.neo.marvelcharacters.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindsModule {
    @Singleton
    @Binds
    abstract fun bindsMarvelRepository(
        repository: MarvelRepositoryImpl
    ): MarvelRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataProvidesModule {
    @Singleton
    @Provides
    fun providesMarvelService(retrofit: Retrofit): MarvelService {
        return retrofit.create(MarvelService::class.java)
    }

    @Singleton
    @Provides
    fun providesMarvelPagingSourceService(service: MarvelService): MarvelPagingSource {
        return MarvelPagingSource(
            service = service
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesClient(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(MarvelApiCredential())
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        ).build()

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(MarvelApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}
