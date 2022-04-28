package com.neo.marvelcharacters.data.remote.service

import com.neo.marvelcharacters.data.remote.response.MarvelCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
    ) : MarvelCharactersResponse
}