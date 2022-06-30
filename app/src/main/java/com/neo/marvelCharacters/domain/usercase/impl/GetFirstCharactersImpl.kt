package com.neo.marvelCharacters.domain.usercase.impl

import com.neo.marvelCharacters.core.MarvelApi.firstCharacters
import com.neo.marvelCharacters.core.Resource
import com.neo.marvelCharacters.domain.model.MarvelCharacter
import com.neo.marvelCharacters.domain.repository.MarvelRepository
import com.neo.marvelCharacters.domain.usercase.GetFirstCharacters
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFirstCharactersImpl @Inject constructor(
    private val repository: MarvelRepository
) : GetFirstCharacters {
    override suspend fun invoke(): Flow<Resource<List<MarvelCharacter>>> {
        return repository.getCharacters(offset = 0, count = firstCharacters)
    }
}