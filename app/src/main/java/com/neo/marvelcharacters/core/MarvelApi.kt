package com.neo.marvelCharacters.core

import com.neo.marvelCharacters.BuildConfig

object MarvelApi {
    const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    const val defaultPageSize = 20
    const val firstCharacters = 5

    val privateKey get() = BuildConfig.SECRET_KEY
    val publicKey get() = BuildConfig.PUBLIC_KEY
}