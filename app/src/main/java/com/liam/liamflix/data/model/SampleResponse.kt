package com.liam.liamflix.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SampleResponse(
    val movie: Movie
)
