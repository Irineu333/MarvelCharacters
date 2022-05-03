package com.neo.marvelcharacters.domain.model

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) {

    data class Thumbnail(val extension: String, val path: String) {
        val url: String get() = "${path.replace("http", "https")}.$extension"
    }
}