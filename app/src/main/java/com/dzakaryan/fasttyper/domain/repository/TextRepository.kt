package com.dzakaryan.fasttyper.domain.repository

interface TextRepository {

    suspend fun getRandomText(): String
}