package com.dzakaryan.fasttyper.domain.datasource

import com.dzakaryan.fasttyper.domain.model.User
import com.dzakaryan.fasttyper.domain.model.WpmStat

interface StatisticsRemoteDataSource {

    suspend fun getStatsForUser(user: User): List<WpmStat>

    suspend fun addStatRecord(stat: WpmStat, user: User): Boolean
}