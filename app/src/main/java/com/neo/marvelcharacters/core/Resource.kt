package com.neo.marvelcharacters.core

import com.neo.marvelcharacters.data.remote.response.MarvelCharactersResponse

sealed class Resource<out T> {
    object Error : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    object Loading : Resource<Nothing>()
}