package com.neo.marvelcharacters.domain.usercase.impl

import com.neo.marvelcharacters.core.MarvelApi.firstCharacters
import com.neo.marvelcharacters.core.Resource
import com.neo.marvelcharacters.domain.model.MarvelCharacter
import com.neo.marvelcharacters.domain.repository.MarvelRepository
import com.neo.marvelcharacters.domain.usercase.GetFirstCharacters
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFirstCharactersImpl @Inject constructor(
    private val repository: MarvelRepository
) : GetFirstCharacters {
    override suspend fun invoke(): Flow<Resource<List<MarvelCharacter>>> {
        return repository.getCharacters(offset = 0, count = firstCharacters)
    }
}