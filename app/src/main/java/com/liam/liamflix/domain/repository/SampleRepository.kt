package com.liam.liamflix.domain.repository

interface SampleRepository {

	suspend fun sample(number: Int): Result<Int>
}