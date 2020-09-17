package com.dzakaryan.fasttyper.domain.datasource.remote

interface TextRemoteDataSource {

    suspend fun getRandomText(sentencesCount: Int): String
}