package com.neo.marvelcharacters.domain.usercase

import com.neo.marvelcharacters.core.Resource
import com.neo.marvelcharacters.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface GetFirstCharacters {
    suspend operator fun invoke(): Flow<Resource<List<MarvelCharacter>>>
}