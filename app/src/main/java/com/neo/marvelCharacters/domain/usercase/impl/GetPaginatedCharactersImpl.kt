package com.neo.marvelCharacters.domain.usercase.impl

import com.neo.marvelCharacters.domain.repository.MarvelRepository
import com.neo.marvelCharacters.domain.usercase.GetPaginatedCharacters
import javax.inject.Inject

class GetPaginatedCharactersImpl @Inject constructor(
    private val repository: MarvelRepository
) : GetPaginatedCharacters {
    override suspend fun invoke() = repository.getPaginatedCharacters()
}