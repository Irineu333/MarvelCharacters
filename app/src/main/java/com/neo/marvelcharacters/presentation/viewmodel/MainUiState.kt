package com.neo.marvelcharacters.presentation.viewmodel

import androidx.paging.PagingData
import com.neo.marvelcharacters.domain.model.MarvelCharacter

data class MainUiState(
    val characters: PagingData<MarvelCharacter> = PagingData.empty(),
    val loading : Boolean = false
)