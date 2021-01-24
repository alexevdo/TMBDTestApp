package com.sano.tmdbtestapp.domain.entity

import java.util.*

data class MovieDetailsEntity (
    val id: Int,
    val title: String?,
    val overview: String?,
    val releaseDate: Date?,
    val posterImagePath: String?)


