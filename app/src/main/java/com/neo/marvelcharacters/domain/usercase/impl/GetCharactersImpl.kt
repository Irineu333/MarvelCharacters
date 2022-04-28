package com.neo.marvelcharacters.domain.usercase.impl

import com.neo.marvelcharacters.domain.repository.MarvelRepository
import com.neo.marvelcharacters.domain.usercase.GetCharacters
import javax.inject.Inject

class GetCharactersImpl @Inject constructor(
    private val repository: MarvelRepository
) : GetCharacters {
    override suspend fun invoke() = repository.getCharacters()
}