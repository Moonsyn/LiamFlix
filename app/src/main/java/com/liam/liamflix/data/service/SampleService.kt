package com.liam.liamflix.data.service

import com.liam.liamflix.data.model.SampleResponse
import retrofit2.Call
import retrofit2.http.GET

interface SampleService {

    @GET("/sample")
    fun fetchSample(): Call<SampleResponse>
}