package com.neo.marvelCharacters.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.neo.marvelCharacters.core.MarvelApi.defaultPageSize
import com.neo.marvelCharacters.data.remote.response.toDomain
import com.neo.marvelCharacters.data.remote.service.MarvelService
import com.neo.marvelCharacters.domain.model.MarvelCharacter

class MarvelPagingSource(
    private val service: MarvelService
) : PagingSource<Int, MarvelCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        try {

            val offset = params.key ?: 0

            val response = service.getCharacters(
                offset = offset,
                limit = defaultPageSize
            )

            val data = response.data

            val nextOffset = offset + data.count

            val prevOffset = if (offset >= defaultPageSize) offset - defaultPageSize else null

            val marvelCharacters = data.results.toDomain()

            return LoadResult.Page(
                data = marvelCharacters,
                prevKey = prevOffset,
                nextKey = nextOffset,
                itemsBefore = offset,
                itemsAfter = nextOffset
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}