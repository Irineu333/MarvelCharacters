package com.neo.marvelcharacters.domain.repository

import androidx.paging.PagingData
import com.neo.marvelcharacters.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun getCharacters(): Flow<PagingData<MarvelCharacter>>
}