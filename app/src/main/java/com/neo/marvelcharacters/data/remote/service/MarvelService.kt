package com.neo.marvelcharacters.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
    ) : Any
}