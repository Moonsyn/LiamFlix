package com.liam.liamflix.domain.usecase

import com.liam.liamflix.domain.repository.SampleRepository
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {

    suspend operator fun invoke(number: Int) = sampleRepository.sample(number)
}