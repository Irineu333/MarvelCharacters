package com.neo.marvelcharacters.core

import com.neo.marvelcharacters.BuildConfig

object MarvelApi {
    const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    val privateKey get() = BuildConfig.SECRET_KEY
    val publicKey get() = BuildConfig.PUBLIC_KEY
}