package com.dzakaryan.fasttyper.di.module

import com.dzakaryan.fasttyper.data.datasource.TextLocalDataSourceImpl
import com.dzakaryan.fasttyper.data.datasource.TextRemoteDataSourceImpl
import com.dzakaryan.fasttyper.data.repository.TextRepositoryImpl
import com.dzakaryan.fasttyper.domain.datasource.local.TextLocalDataSource
import com.dzakaryan.fasttyper.domain.datasource.remote.TextRemoteDataSource
import com.dzakaryan.fasttyper.domain.repository.TextRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<TextRemoteDataSource> { TextRemoteDataSourceImpl() }

    single<TextLocalDataSource> { TextLocalDataSourceImpl() }

    single<TextRepository> { TextRepositoryImpl(get(), get()) }
}