package com.sano.tmdbtestapp.data.network

import com.sano.tmdbtestapp.data.network.pojo.MovieDetailsModel
import com.sano.tmdbtestapp.data.network.pojo.PagedResponse

interface INetworkDataSource {
    suspend fun discoverMovies(page: Int): PagedResponse?
    suspend fun searchMovies(query: String): PagedResponse?
    suspend fun getMovieDetails(movieId: Int): MovieDetailsModel?
}