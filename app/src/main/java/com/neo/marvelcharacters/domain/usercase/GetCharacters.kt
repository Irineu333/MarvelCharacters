package com.neo.marvelcharacters.domain.usercase

import androidx.paging.PagingData
import com.neo.marvelcharacters.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface GetCharacters {
    suspend operator fun invoke(): Flow<PagingData<MarvelCharacter>>
}