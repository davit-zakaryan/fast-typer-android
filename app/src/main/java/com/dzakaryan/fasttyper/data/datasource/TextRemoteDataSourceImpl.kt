package com.dzakaryan.fasttyper.data.datasource

import com.dzakaryan.fasttyper.data.api.RandomTextApi
import com.dzakaryan.fasttyper.domain.datasource.remote.TextRemoteDataSource
import kotlinx.coroutines.Dispatchers

class TextRemoteDataSourceImpl(
    private val randomTextApi: RandomTextApi,
) : TextRemoteDataSource, RetrofitDataSource {

    private var sentences: Int = 10
    private val url = "http://metaphorpsum.com/sentences/$sentences"

    override suspend fun getRandomText(): String {
        val responseResult = safeApiCall(Dispatchers.IO) {
            randomTextApi.get(url)
        }
        return when (responseResult) {
            is ResultWrapper.Success -> {
                responseResult.payload.string()
            }
            else -> "Some  default text"
        }
    }
}