package com.liam.liamflix.data.repository

import com.liam.liamflix.data.datasource.SampleDataSource
import com.liam.liamflix.domain.repository.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
	private val sampleDataSource: SampleDataSource
): SampleRepository {

	override suspend fun sample(number: Int): Result<Int> {
		return sampleDataSource.sample(number)
	}
}