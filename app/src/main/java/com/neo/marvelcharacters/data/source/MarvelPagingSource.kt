package com.neo.marvelcharacters.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.neo.marvelcharacters.core.MarvelApi.defaultPageSize
import com.neo.marvelcharacters.data.remote.response.toDomain
import com.neo.marvelcharacters.data.remote.service.MarvelService
import com.neo.marvelcharacters.domain.model.MarvelCharacter
import retrofit2.HttpException
import java.io.IOException

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

            val currentKey = params.key
            val nextKey = currentKey?.plus(defaultPageSize) ?: 0
            val prevKey = currentKey?.let {
                if (currentKey > defaultPageSize) currentKey - defaultPageSize else null
            }

            val response = service.getCharacters(
                offset = nextKey,
                limit = defaultPageSize
            )

            val marvelCharacters = response.data.results.toDomain()

            return LoadResult.Page(
                data = marvelCharacters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}