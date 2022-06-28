package com.neo.marvelCharacters.domain.usercase

import com.neo.marvelCharacters.core.Resource
import com.neo.marvelCharacters.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface GetFirstCharacters {
    suspend operator fun invoke(): Flow<Resource<List<MarvelCharacter>>>
}