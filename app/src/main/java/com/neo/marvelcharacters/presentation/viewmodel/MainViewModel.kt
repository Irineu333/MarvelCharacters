package com.neo.marvelcharacters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.neo.marvelcharacters.domain.usercase.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState get() = _uiState.asStateFlow()

    fun getAllCharacters() = viewModelScope.launch {
        getCharacters().cachedIn(this)
            .onStart {
                _uiState.update {
                    it.copy(
                        loading = true
                    )
                }
            }.collect { characters ->
                _uiState.update { state ->
                    state.copy(
                        characters = characters,
                        loading = false
                    )
                }
            }
    }
}