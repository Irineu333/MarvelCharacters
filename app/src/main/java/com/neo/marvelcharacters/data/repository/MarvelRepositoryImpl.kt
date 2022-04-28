package com.neo.marvelcharacters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.neo.marvelcharacters.core.MarvelApi.defaultPageSize
import com.neo.marvelcharacters.data.remote.service.MarvelService
import com.neo.marvelcharacters.data.source.MarvelPagingSource
import com.neo.marvelcharacters.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val service: MarvelService
) : MarvelRepository {

    override suspend fun getCharacters() = Pager(
        config = PagingConfig(
            pageSize = defaultPageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MarvelPagingSource(
                service = service
            )
        }
    ).flow
}