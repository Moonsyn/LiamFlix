package com.liam.liamflix.data.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.liam.liamflix.data.network.adapter.CallAdapterFactory
import com.liam.liamflix.data.service.SampleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECT_TIMEOUT = 120L
    private const val READ_TIMEOUT = 120L
    private const val WRITE_TIMEOUT = 120L
    private val contentType = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val jsonConfig = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        return Retrofit.Builder()
            .baseUrl("baseUrl")
            .client(okHttpClient)
            .addConverterFactory(jsonConfig.asConverterFactory(contentType))
            .addCallAdapterFactory(CallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideSampleService(retrofit: Retrofit): SampleService {
        return retrofit.create(SampleService::class.java)
    }
}