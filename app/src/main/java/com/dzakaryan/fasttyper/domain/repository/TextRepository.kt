package com.dzakaryan.fasttyper.domain.repository

interface TextRepository {

    suspend fun getRandomText(sentencesCount: Int): String
}