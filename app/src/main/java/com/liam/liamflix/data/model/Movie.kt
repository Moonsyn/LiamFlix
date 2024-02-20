package com.liam.liamflix.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val movieId: String,
    val title: String,
    val directorNm: String,
    val directorId: String,
    val actorNm: String,
    val actorId: String,
    val nation: String,
    val company: String,
    val prodYear: Int,
    val runtime: Int,
    val genre: String,
    val kmdbUrl: String,
    val type: String,
    val use: String,
    val ratingGrade: String,
    val releaseDate: String,
    val keywords: String,
    val posterUrl: String,
    val stillUrl: String
)