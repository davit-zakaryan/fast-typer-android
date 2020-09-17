package com.dzakaryan.fasttyper.domain.datasource.local

interface TextLocalDataSource {

    suspend fun getRandomText(sentencesCount: Int): String
}