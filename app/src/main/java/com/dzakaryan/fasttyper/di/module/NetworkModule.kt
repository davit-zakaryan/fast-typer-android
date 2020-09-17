package com.dzakaryan.fasttyper.di.module

import com.dzakaryan.fasttyper.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule: Module = module {

    factory { HttpLoggingInterceptor() }


    // Gson
    single { provideGson() }
    //factory { provideOkHttpClient(get()) }

    // OkHttpClient
    single {
        val okHttpBuilder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        // Also you can add your own interceptor
        okHttpBuilder
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(
                10,
                TimeUnit.MINUTES
            ) // fix exception when uploading files with slow internet
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .setPrettyPrinting()
        .create()
}

//fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
//    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
//}

//fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create()).build()
//}
//

//
//fun provideForecastApi(retrofit: Retrofit): WeatherForecastApi = retrofit.create(WeatherForecastApi::class.java)


object OkHttpProps {
    const val CACHE_SIZE = 10 * 1024 * 1024L
    const val CONNECTION_TIMEOUT = 30L
    const val READ_WRITE_TIMEOUT = 30L
}
