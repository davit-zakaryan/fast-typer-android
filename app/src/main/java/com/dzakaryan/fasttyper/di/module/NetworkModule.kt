package com.dzakaryan.fasttyper.di.module

import com.dzakaryan.fasttyper.BuildConfig
import com.dzakaryan.fasttyper.data.api.RandomTextApi
import com.dzakaryan.fasttyper.di.module.OkHttpProps.BASE_URL
import com.dzakaryan.fasttyper.di.module.OkHttpProps.CONNECTION_TIMEOUT
import com.dzakaryan.fasttyper.di.module.OkHttpProps.READ_WRITE_TIMEOUT
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule: Module = module {

    factory { provideHttpLogging() }
    factory { provideOkHttpClient(get()) }
    factory { provideRandomTextApi(get()) }

    single { provideRetrofit(get()) }
    single { provideGson() }
}

fun provideHttpLogging(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.BASIC
    }
    return interceptor
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}


fun provideGson(): Gson {
    return GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .setPrettyPrinting()
        .create()
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideRandomTextApi(retrofit: Retrofit): RandomTextApi =
    retrofit.create(RandomTextApi::class.java)


object OkHttpProps {
    const val CONNECTION_TIMEOUT = 30L
    const val READ_WRITE_TIMEOUT = 30L
    const val BASE_URL = "http://dzakaryan.com" //This is fictive url
}
