package com.dzakaryan.fasttyper.domain.repository

import com.dzakaryan.fasttyper.domain.model.User

interface UserRepository {

    suspend fun saveAuthenticatedUser(user: User?)

    fun getUser(): User

    fun getStatistics()

    fun saveStatistics()
}