package com.dzakaryan.fasttyper.data.repository

import com.dzakaryan.fasttyper.domain.datasource.StatisticsRemoteDataSource
import com.dzakaryan.fasttyper.domain.model.User
import com.dzakaryan.fasttyper.domain.model.WpmStat
import com.dzakaryan.fasttyper.domain.repository.HistoryRepository

class HistoryRepositoryImpl(
    private val statisticsRemoteDataSource: StatisticsRemoteDataSource,
) : HistoryRepository {

    override suspend fun requestAllHistory(user: User): List<WpmStat> {
        return statisticsRemoteDataSource.getStatsForUser(user)
    }

    override suspend fun saveLastResult(user: User, wpmStat: WpmStat): Boolean {
        return statisticsRemoteDataSource.addStatRecord(wpmStat, user)
    }
}