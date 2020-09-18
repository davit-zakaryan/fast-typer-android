package com.dzakaryan.fasttyper.domain.repository

import com.dzakaryan.fasttyper.domain.model.User
import com.dzakaryan.fasttyper.domain.model.WpmStat

interface HistoryRepository {

    suspend fun requestAllHistory(user: User): List<WpmStat>
}