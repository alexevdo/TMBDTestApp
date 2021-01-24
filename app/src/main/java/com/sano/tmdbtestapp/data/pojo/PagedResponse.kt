package com.sano.tmdbtestapp.data.pojo

import com.google.gson.annotations.SerializedName

data class PagedResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieModel>? = null,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)