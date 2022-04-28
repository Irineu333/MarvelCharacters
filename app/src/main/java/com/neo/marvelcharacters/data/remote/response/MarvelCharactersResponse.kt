package com.neo.marvelcharacters.data.remote.response

import com.google.gson.annotations.SerializedName
import com.neo.marvelcharacters.domain.model.MarvelCharacter

data class MarvelCharactersResponse(
    @SerializedName("data")
    val data : Data
) {
    data class Data(
        @SerializedName("count")
        val count: Int,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("results")
        val results: List<Result>,
        @SerializedName("total")
        val total: Int
    ) {
        data class Result(
            @SerializedName("id")
            val id: Int,
            @SerializedName("description")
            val description: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("thumbnail")
            val thumbnail: Thumbnail
        ) {
            data class Thumbnail(
                @SerializedName("extension")
                val extension: String,
                @SerializedName("path")
                val path: String
            )
        }
    }
}

fun List<MarvelCharactersResponse.Data.Result>.toDomain() = map { it.toDomain() }

fun MarvelCharactersResponse.Data.Result.toDomain() =
    MarvelCharacter(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail.toDomain()
    )

fun MarvelCharactersResponse.Data.Result.Thumbnail.toDomain() =
    MarvelCharacter.Thumbnail(
        extension = extension,
        path = path
    )
