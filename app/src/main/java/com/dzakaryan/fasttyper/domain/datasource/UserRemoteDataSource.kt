package com.dzakaryan.fasttyper.domain.datasource

import com.dzakaryan.fasttyper.domain.model.User

interface UserRemoteDataSource {

    suspend fun saveAuthenticatedUser(user: User): Boolean
}