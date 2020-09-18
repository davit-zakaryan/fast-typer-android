package com.dzakaryan.fasttyper.data.datasource

import com.dzakaryan.fasttyper.data.datasource.FirestoreConstants.STATS
import com.dzakaryan.fasttyper.data.datasource.FirestoreConstants.USERS
import com.dzakaryan.fasttyper.domain.datasource.remote.StatisticsRemoteDataSource
import com.dzakaryan.fasttyper.domain.model.User
import com.dzakaryan.fasttyper.domain.model.WpmStat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class StatisticsRemoteDataSourceImpl : StatisticsRemoteDataSource {

    private val usersRef = Firebase.firestore.collection(USERS)

    override suspend fun getStatsForUser(user: User): List<WpmStat> {
        TODO("Not yet implemented")
    }

    override suspend fun addStatRecord(stat: WpmStat, user: User) {
        usersRef.document(user.uid).collection(STATS).add(
            WpmStat(
                value = Random.nextInt(),
                created = System.currentTimeMillis(),
                userId = user.uid
            )
        )
    }
}