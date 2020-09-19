package com.dzakaryan.fasttyper.data.repository

import com.dzakaryan.fasttyper.domain.datasource.remote.UserLocalDataSource
import com.dzakaryan.fasttyper.domain.datasource.remote.UserRemoteDataSource
import com.dzakaryan.fasttyper.domain.model.User
import com.dzakaryan.fasttyper.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : UserRepository {

    override suspend fun saveAuthenticatedUser(user: User?) {
        user?.let {
            userRemoteDataSource.saveAuthenticatedUser(user)
            userLocalDataSource.setUser(user)
        }
    }

    override suspend fun getUser(): User {
        return userLocalDataSource.getUser()
    }
}