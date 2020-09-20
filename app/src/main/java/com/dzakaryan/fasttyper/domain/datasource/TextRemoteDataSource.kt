package com.dzakaryan.fasttyper.domain.datasource

interface TextRemoteDataSource {

    suspend fun getRandomText(): String
}