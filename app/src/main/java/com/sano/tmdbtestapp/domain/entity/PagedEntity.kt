package com.sano.tmdbtestapp.domain.entity

data class PagedEntity<T>(
    val values: List<T>?,
    val page: Int,
    val totalResults: Int,
    val totalPages: Int
)