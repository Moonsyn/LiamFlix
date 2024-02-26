package com.liam.liamflix.data.datasource

import javax.inject.Inject

class SampleDataSourceImpl @Inject constructor(): SampleDataSource {

	override suspend fun sample(number: Int): Result<Int> {
		return Result.success(number+1)
	}
}