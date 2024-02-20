package com.liam.liamflix.data.network.adapter

import com.liam.liamflix.data.network.model.Response
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // suspend functions wrap the response type in 'Call'
        // return Type이 Call로 감싸져 있는가?
        if (Call::class.java != getRawType(returnType)) {
            return null
        }
        // check the return type is 'ParameterizedType'
        // returnType이 제네릭 인자를 가지는가?
        // Call<Response<<Foo>> or Call<Response<out Foo>>
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<Response<<Foo>> or Call<Response<out Foo>>"
        }
        // get the response type inside the 'Call' type
        // returnType에서 첫번째 제네릭 인자를 얻는다. Response<out Foo>
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not ApiResponse then we can't handle this type, so we return null
        // 기대한 것처럼 동작하기 위해서는 추출한 제네릭 인자가 내가 만든 Response타입이어야함.
        if (getRawType(responseType) != Response::class.java) {
            return null
        }
        // the response type is ApiResponse and should be parameterized
        // 제네릭 인자 가지는지 확인 Response<Foo> or Response<out Foo>
        check(responseType is ParameterizedType) {
            "Response must be parameterized as Response<Foo> or Response<out Foo>"
        }
        // Foo를 얻어서 CallAdapter를 생성한다.
        val successBodyType = getParameterUpperBound(0, responseType)
        return ResponseCallAdapter<Any>(successBodyType)
    }
}