package com.dzakaryan.fasttyper.data.repository

import com.dzakaryan.fasttyper.domain.datasource.local.TextLocalDataSource
import com.dzakaryan.fasttyper.domain.datasource.remote.TextRemoteDataSource
import com.dzakaryan.fasttyper.domain.repository.TextRepository

class TextRepositoryImpl(
    private val cacheDataSource: TextLocalDataSource,
    private val remoteDataSource: TextRemoteDataSource
) : TextRepository {

    private var sentences: Int = 10
    private val url = "http://metaphorpsum.com/sentences/{$sentences}}"
    private var useCashed = false

    override suspend fun getRandomText(sentencesCount: Int) = if (useCashed) {
        cacheDataSource.getRandomText(sentencesCount)
    } else {
        remoteDataSource.getRandomText(sentencesCount)
    }
}