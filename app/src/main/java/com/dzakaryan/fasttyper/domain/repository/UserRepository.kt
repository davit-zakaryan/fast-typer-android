package com.dzakaryan.fasttyper.domain.repository

import com.dzakaryan.fasttyper.domain.model.User

interface UserRepository {

    suspend fun saveAuthenticatedUser(user: User?)

    suspend fun getUser(): User
}