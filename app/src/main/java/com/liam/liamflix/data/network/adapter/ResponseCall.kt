package com.liam.liamflix.data.network.adapter

import com.liam.liamflix.data.network.model.Response
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import java.io.IOException

class ResponseCall<T: Any>(
    private val delegate: Call<T>
): Call<Response<T>> {
    override fun clone(): Call<Response<T>> = ResponseCall(delegate.clone())

    override fun execute(): retrofit2.Response<Response<T>> {
        throw UnsupportedOperationException("ResponseCall doesn't support execute")
    }

    override fun enqueue(callback: Callback<Response<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@ResponseCall,
                        retrofit2.Response.success(Response.Success(response.body()))
                    )
                } else {
                    callback.onResponse(
                        this@ResponseCall,
                        retrofit2.Response.success(
                            Response.Failure(
                                response.code(),
                                response.errorBody()?.string()
                            )
                        )
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResponse = when (t) {
                    is IOException -> Response.NetworkError(t)
                    else -> Response.UnExpected(t)
                }
                callback.onResponse(this@ResponseCall, retrofit2.Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}