package com.dzakaryan.fasttyper.data.datasource

import com.dzakaryan.fasttyper.domain.datasource.local.TextLocalDataSource

class TextLocalDataSourceImpl : TextLocalDataSource {

    override suspend fun getRandomText(sentencesCount: Int): String {
        TODO("Not yet implemented")
    }
}