package com.liam.liamflix.domain.usecase

import com.liam.liamflix.domain.repository.SampleRepository
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {

    operator fun invoke() = sampleRepository.sample()
}