package com.neo.marvelCharacters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.neo.marvelCharacters.core.MarvelApi.defaultPageSize
import com.neo.marvelCharacters.core.Resource
import com.neo.marvelCharacters.data.remote.response.toDomain
import com.neo.marvelCharacters.data.remote.service.MarvelService
import com.neo.marvelCharacters.data.source.MarvelPagingSource
import com.neo.marvelCharacters.domain.model.MarvelCharacter
import com.neo.marvelCharacters.domain.repository.MarvelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val service: MarvelService,
    private val pagingSource: MarvelPagingSource
) : MarvelRepository {

    override suspend fun getPaginatedCharacters() = Pager(
        config = PagingConfig(
            pageSize = defaultPageSize,
            enablePlaceholders = true,
            initialLoadSize = defaultPageSize
        ),
        pagingSourceFactory = {
            pagingSource
        }
    ).flow

    override suspend fun getCharacters(
        offset: Int, count: Int
    ): Flow<Resource<List<MarvelCharacter>>> {
        return flow {
            runCatching {

                emit(Resource.Loading)

                val response = withContext(Dispatchers.IO) {
                    service.getCharacters(
                        offset = offset,
                        limit = count
                    )
                }

                val data = response.data

                emit(Resource.Success(data.results.toDomain()))
            }.onFailure {
                emit(Resource.Error)
            }
        }
    }
}