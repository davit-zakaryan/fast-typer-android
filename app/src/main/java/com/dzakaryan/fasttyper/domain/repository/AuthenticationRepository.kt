package com.dzakaryan.fasttyper.domain.repository

interface AuthenticationRepository {

    suspend fun signIn()

    suspend fun signOut()
}