package com.neo.marvelCharacters.data.remote.service

import com.neo.marvelCharacters.data.remote.response.MarvelCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
    ) : MarvelCharactersResponse
}