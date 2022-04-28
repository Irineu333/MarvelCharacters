package com.neo.marvelcharacters.domain.di

import com.neo.marvelcharacters.data.repository.MarvelRepositoryImpl
import com.neo.marvelcharacters.domain.repository.MarvelRepository
import com.neo.marvelcharacters.domain.usercase.GetCharacters
import com.neo.marvelcharacters.domain.usercase.impl.GetCharactersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun bindsGetCharacters(
        getCharacters: GetCharactersImpl
    ): GetCharacters
}