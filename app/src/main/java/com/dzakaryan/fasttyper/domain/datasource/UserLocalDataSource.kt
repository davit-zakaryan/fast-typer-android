package com.dzakaryan.fasttyper.domain.datasource

import com.dzakaryan.fasttyper.domain.model.User

interface UserLocalDataSource {

    suspend fun getUser(): User

    suspend fun setUser(user: User)
}