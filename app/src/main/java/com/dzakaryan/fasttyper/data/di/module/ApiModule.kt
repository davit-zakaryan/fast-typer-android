package com.dzakaryan.fasttyper.data.di.module

import com.dzakaryan.fasttyper.data.api.RandomTextApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRandomTextApi(retrofit: Retrofit): RandomTextApi? {
        return retrofit.create<RandomTextApi>(RandomTextApi::class.java)
    }
}