package com.sano.tmdbtestapp.data

import com.sano.tmdbtestapp.data.pojo.MovieDetailsModel
import com.sano.tmdbtestapp.data.pojo.PagedResponse

interface INetworkDataSource {
    suspend fun discoverMovies(page: Int): PagedResponse?
    suspend fun searchMovies(query: String): PagedResponse?
    suspend fun getMovieDetails(movieId: Int): MovieDetailsModel?
}