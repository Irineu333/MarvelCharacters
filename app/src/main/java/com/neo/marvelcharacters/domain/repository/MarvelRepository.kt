package com.neo.marvelcharacters.domain.repository

import androidx.paging.PagingData
import com.neo.marvelcharacters.core.Resource
import com.neo.marvelcharacters.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun getPaginatedCharacters(): Flow<PagingData<MarvelCharacter>>
    suspend fun getCharacters(offset: Int, count: Int): Flow<Resource<List<MarvelCharacter>>>
}