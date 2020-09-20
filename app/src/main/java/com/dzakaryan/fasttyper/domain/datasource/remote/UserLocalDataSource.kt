package com.dzakaryan.fasttyper.domain.datasource.remote

import com.dzakaryan.fasttyper.domain.model.User

interface UserLocalDataSource {

    suspend fun getUser(): User

    suspend fun setUser(user: User)
}