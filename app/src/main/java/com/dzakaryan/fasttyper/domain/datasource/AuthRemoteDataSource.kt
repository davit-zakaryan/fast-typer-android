package com.dzakaryan.fasttyper.domain.datasource

interface AuthRemoteDataSource {

    suspend fun sighIn()

    suspend fun signOut()
}