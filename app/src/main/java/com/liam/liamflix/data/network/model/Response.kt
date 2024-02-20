package com.liam.liamflix.data.network.model

import java.io.IOException

sealed class Response<out T: Any> {
    // status code == 200
    data class Success<T: Any>(val body: T?) : Response<T>()
    // status code != 200
    data class Failure(val code: Int, val error: String?) : Response<Nothing>()
    // Network Error
    data class NetworkError(val exception: IOException) : Response<Nothing>()
    // 기타 에러
    data class UnExpected(val t: Throwable?) : Response<Nothing>()
}