package com.sano.tmdbtestapp.data.network.pojo

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: Any? = null,
    @SerializedName("popularity")
    val popularity: Float? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Float? = null
)