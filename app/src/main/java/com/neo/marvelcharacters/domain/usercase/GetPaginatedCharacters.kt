package com.neo.marvelCharacters.domain.usercase

import androidx.paging.PagingData
import com.neo.marvelCharacters.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface GetPaginatedCharacters {
    suspend operator fun invoke(): Flow<PagingData<MarvelCharacter>>
}