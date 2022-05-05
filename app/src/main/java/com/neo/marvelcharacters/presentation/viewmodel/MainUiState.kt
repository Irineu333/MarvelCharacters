package com.neo.marvelcharacters.presentation.viewmodel

import androidx.paging.PagingData
import com.neo.marvelcharacters.domain.model.MarvelCharacter

data class MainUiState(
    val paginatedCharacters: PagingData<MarvelCharacter> = PagingData.empty(),
    val firstCharacters: List<MarvelCharacter> = emptyList(),
    val firstCharactersLoading : Boolean = false
)