package com.dzakaryan.fasttyper.domain.repository

interface IUserRepository {

    fun getUser()

    fun getStatistics()

    fun saveStatistics()
}