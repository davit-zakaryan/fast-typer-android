package com.dzakaryan.fasttyper.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RandomTextApi {

    @GET
    suspend fun get(@Url url: String): Response<ResponseBody>
}