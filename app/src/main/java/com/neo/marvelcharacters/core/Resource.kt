package com.neo.marvelCharacters.core

sealed class Resource<out T> {
    object Error : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    object Loading : Resource<Nothing>()
}