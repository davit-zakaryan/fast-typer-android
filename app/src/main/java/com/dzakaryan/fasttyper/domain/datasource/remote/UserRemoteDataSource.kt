package com.dzakaryan.fasttyper.domain.datasource.remote

import com.dzakaryan.fasttyper.domain.model.User

interface UserRemoteDataSource {


    suspend fun saveUser(user: User)
}