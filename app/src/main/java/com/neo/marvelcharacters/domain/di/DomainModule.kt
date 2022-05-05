package com.neo.marvelcharacters.domain.di

import com.neo.marvelcharacters.domain.usercase.GetFirstCharacters
import com.neo.marvelcharacters.domain.usercase.GetPaginatedCharacters
import com.neo.marvelcharacters.domain.usercase.impl.GetFirstCharactersImpl
import com.neo.marvelcharacters.domain.usercase.impl.GetPaginatedCharactersImpl
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
    abstract fun bindsGetPaginatedCharacters(
        getCharacters: GetPaginatedCharactersImpl
    ): GetPaginatedCharacters

    @Singleton
    @Binds
    abstract fun bindsGetFirstCharacters(
        getPaginatedCharacters: GetFirstCharactersImpl
    ): GetFirstCharacters
}