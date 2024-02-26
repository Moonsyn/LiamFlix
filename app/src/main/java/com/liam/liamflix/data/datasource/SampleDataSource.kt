package com.liam.liamflix.data.datasource

interface SampleDataSource {

	suspend fun sample(number: Int): Result<Int>
}