package com.neo.marvelCharacters.presentation.viewmodel

import androidx.paging.PagingData
import com.neo.marvelCharacters.domain.model.MarvelCharacter

data class MainUiState(
    val paginatedCharacters: PagingData<MarvelCharacter> = PagingData.empty(),
    val firstCharacters: List<MarvelCharacter> = emptyList(),
    val firstCharactersLoading : Boolean = false
)