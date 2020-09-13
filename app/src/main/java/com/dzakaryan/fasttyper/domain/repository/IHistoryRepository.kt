package com.dzakaryan.fasttyper.domain.repository

import com.dzakaryan.fasttyper.domain.model.WpmStat

interface IHistoryRepository {

    suspend fun requestAllHistory(): List<WpmStat>
}