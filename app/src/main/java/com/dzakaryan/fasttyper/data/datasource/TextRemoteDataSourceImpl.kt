package com.dzakaryan.fasttyper.data.datasource

import com.dzakaryan.fasttyper.domain.datasource.remote.TextRemoteDataSource

class TextRemoteDataSourceImpl : TextRemoteDataSource {

    override suspend fun getRandomText(sentencesCount: Int): String {
        TODO("Not yet implemented")
    }
}