package com.neo.marvelcharacters.domain.usercase.impl

import com.neo.marvelcharacters.domain.repository.MarvelRepository
import com.neo.marvelcharacters.domain.usercase.GetPaginatedCharacters
import javax.inject.Inject

class GetPaginatedCharactersImpl @Inject constructor(
    private val repository: MarvelRepository
) : GetPaginatedCharacters {
    override suspend fun invoke() = repository.getPaginatedCharacters()
}