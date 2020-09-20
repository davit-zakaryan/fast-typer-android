package com.dzakaryan.fasttyper.data.repository

import com.dzakaryan.fasttyper.domain.datasource.remote.AuthRemoteDataSource
import com.dzakaryan.fasttyper.domain.repository.AuthenticationRepository

class AuthRepositoryImpl(
    val authRemoteDataSource: AuthRemoteDataSource,
) : AuthenticationRepository {


    override suspend fun signIn() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        authRemoteDataSource.signOut()
    }
}