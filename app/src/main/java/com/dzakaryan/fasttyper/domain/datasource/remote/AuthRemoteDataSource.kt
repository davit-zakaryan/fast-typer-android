package com.dzakaryan.fasttyper.domain.datasource.remote

interface AuthRemoteDataSource {

    suspend fun sighIn()

    suspend fun signOut()
}