package com.sano.tmdbtestapp.data.service

import com.sano.tmdbtestapp.data.pojo.MovieDetailsModel
import com.sano.tmdbtestapp.data.pojo.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun discoverMovies(@Query("page") page: Int): PagedResponse?

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String): PagedResponse?

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailsModel?

}