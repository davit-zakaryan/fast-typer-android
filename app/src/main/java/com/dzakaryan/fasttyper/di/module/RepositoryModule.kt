package com.dzakaryan.fasttyper.di.module

import com.dzakaryan.fasttyper.data.datasource.AuthFirebaseDataSourceImpl
import com.dzakaryan.fasttyper.data.datasource.StatisticsRemoteDataSourceImpl
import com.dzakaryan.fasttyper.data.datasource.TextRemoteDataSourceImpl
import com.dzakaryan.fasttyper.data.datasource.UserRemoteDataSourceImpl
import com.dzakaryan.fasttyper.data.repository.AuthRepositoryImpl
import com.dzakaryan.fasttyper.data.repository.HistoryRepositoryImpl
import com.dzakaryan.fasttyper.data.repository.TextRepositoryImpl
import com.dzakaryan.fasttyper.data.repository.UserRepositoryImpl
import com.dzakaryan.fasttyper.domain.datasource.remote.AuthRemoteDataSource
import com.dzakaryan.fasttyper.domain.datasource.remote.StatisticsRemoteDataSource
import com.dzakaryan.fasttyper.domain.datasource.remote.TextRemoteDataSource
import com.dzakaryan.fasttyper.domain.datasource.remote.UserRemoteDataSource
import com.dzakaryan.fasttyper.domain.repository.AuthenticationRepository
import com.dzakaryan.fasttyper.domain.repository.HistoryRepository
import com.dzakaryan.fasttyper.domain.repository.TextRepository
import com.dzakaryan.fasttyper.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    //Text
    single<TextRemoteDataSource> { TextRemoteDataSourceImpl() }

    single<TextRepository> { TextRepositoryImpl(get()) }

    //User
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl() }

    single<UserRepository> { UserRepositoryImpl(get()) }

    //Auth
    single<AuthRemoteDataSource> { AuthFirebaseDataSourceImpl() }

    single<AuthenticationRepository> { AuthRepositoryImpl(get()) }

    //History
    single<StatisticsRemoteDataSource> { StatisticsRemoteDataSourceImpl() }

    single<HistoryRepository> { HistoryRepositoryImpl(get()) }
}