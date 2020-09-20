package com.dzakaryan.fasttyper.data.datasource

sealed class ResultWrapper<out T> {

    data class Success<T>(val payload: T) : ResultWrapper<T>()
    data class Error<T>(val msg: String) : ResultWrapper<Nothing>()
}