package com.neo.marvelCharacters.domain.di

import com.neo.marvelCharacters.domain.usercase.GetFirstCharacters
import com.neo.marvelCharacters.domain.usercase.GetPaginatedCharacters
import com.neo.marvelCharacters.domain.usercase.impl.GetFirstCharactersImpl
import com.neo.marvelCharacters.domain.usercase.impl.GetPaginatedCharactersImpl
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