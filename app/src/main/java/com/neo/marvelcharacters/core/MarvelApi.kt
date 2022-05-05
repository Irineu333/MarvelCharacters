package com.neo.marvelcharacters.core

import com.neo.marvelcharacters.BuildConfig

object MarvelApi {
    const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    const val defaultPageSize = 20
    const val firstCharacters = 5

    val privateKey get() = BuildConfig.SECRET_KEY
    val publicKey get() = BuildConfig.PUBLIC_KEY
}