package com.dzakaryan.fasttyper.data.repository

import com.dzakaryan.fasttyper.domain.datasource.remote.TextRemoteDataSource
import com.dzakaryan.fasttyper.domain.repository.TextRepository

class TextRepositoryImpl(
    private val remoteDataSource: TextRemoteDataSource,
) : TextRepository {

    override suspend fun getRandomText() = remoteDataSource.getRandomText()
}