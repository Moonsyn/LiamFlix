package com.liam.liamflix.data.di

import com.liam.liamflix.data.datasource.SampleDataSource
import com.liam.liamflix.data.datasource.SampleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindSampleDataSource(
        sampleDataSourceImpl: SampleDataSourceImpl
    ): SampleDataSource
}