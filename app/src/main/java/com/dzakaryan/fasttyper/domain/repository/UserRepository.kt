package com.dzakaryan.fasttyper.domain.repository

interface UserRepository {

    fun getUser()

    fun getStatistics()

    fun saveStatistics()
}