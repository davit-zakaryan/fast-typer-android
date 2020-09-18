package com.dzakaryan.fasttyper.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dzakaryan.fasttyper.data.datasource.*
import com.dzakaryan.fasttyper.data.repository.AuthRepositoryImpl
import com.dzakaryan.fasttyper.data.repository.HistoryRepositoryImpl
import com.dzakaryan.fasttyper.data.repository.TextRepositoryImpl
import com.dzakaryan.fasttyper.data.repository.UserRepositoryImpl
import com.dzakaryan.fasttyper.domain.datasource.remote.*
import com.dzakaryan.fasttyper.domain.repository.AuthenticationRepository
import com.dzakaryan.fasttyper.domain.repository.HistoryRepository
import com.dzakaryan.fasttyper.domain.repository.TextRepository
import com.dzakaryan.fasttyper.domain.repository.UserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    //Text
    single<TextRemoteDataSource> { TextRemoteDataSourceImpl() }

    single<TextRepository> { TextRepositoryImpl(get()) }

    //User
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl() }

    single<UserLocalDataSource> { SharedPreferencesHelper(get(), get()) }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    //Auth
    single<AuthRemoteDataSource> { AuthFirebaseDataSourceImpl() }

    single<AuthenticationRepository> { AuthRepositoryImpl(get()) }

    //History
    single<StatisticsRemoteDataSource> { StatisticsRemoteDataSourceImpl() }

    single<HistoryRepository> { HistoryRepositoryImpl(get()) }

    //Shared Preferences
    single<SharedPreferences> { getSharedPrefs(androidApplication()) }
}

private fun getSharedPrefs(application: Application): SharedPreferences {
    return application.getSharedPreferences(application.packageName, Context.MODE_PRIVATE)
}