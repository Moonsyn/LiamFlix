package com.liam.liamflix.data.network.adapter

import com.liam.liamflix.data.network.model.Response
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResponseCallAdapter<T: Any>(
    private val responseType: Type
): CallAdapter<T, Call<Response<T>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<T>): Call<Response<T>> {
        return ResponseCall(call)
    }
}