package com.dzakaryan.fasttyper.data.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

interface RetrofitDataSource {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> Response<T>,
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        ResultWrapper.Success(it)
                    } ?: ResultWrapper.Error<Nothing>("${response.code()} ${response.message()}")
                } else {
                    ResultWrapper.Error<Nothing>("${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                ResultWrapper.Error<Nothing>(e.message ?: e.toString())
            }
        }
    }
}