package com.dzakaryan.fasttyper.domain.repository

interface IAuthenticationRepository {

    suspend fun signIn()

    suspend fun signOut()
}