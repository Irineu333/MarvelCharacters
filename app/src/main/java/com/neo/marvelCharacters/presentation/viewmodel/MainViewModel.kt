package com.neo.marvelCharacters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.neo.marvelCharacters.core.Resource
import com.neo.marvelCharacters.domain.usercase.GetFirstCharacters
import com.neo.marvelCharacters.domain.usercase.GetPaginatedCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPaginatedCharacters: GetPaginatedCharacters,
    private val getFirstCharacters: GetFirstCharacters,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<MainUiEffect>()
    val uiEffect get() = _uiEffect.asSharedFlow()

    fun requestFirstCharacters() = viewModelScope.launch {
        getFirstCharacters().collect { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            firstCharacters = result.data,
                            firstCharactersLoading = false,
                        )
                    }
                }
                Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            firstCharactersLoading = false,
                        )
                    }

                    _uiEffect.emit(MainUiEffect.Error)
                }
                Resource.Loading -> {
                    _uiState.update { state ->
                        state.copy(
                            firstCharactersLoading = true
                        )
                    }
                }
            }
        }
    }

    fun requestPaginatedCharacters() = viewModelScope.launch {
        getPaginatedCharacters().cachedIn(this)
            .collect { paginatedCharacters ->
                _uiState.update { state ->
                    state.copy(
                        paginatedCharacters = paginatedCharacters
                    )
                }
            }
    }
}