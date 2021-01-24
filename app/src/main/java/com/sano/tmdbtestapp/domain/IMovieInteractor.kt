package com.sano.tmdbtestapp.domain

import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity

interface IMovieInteractor {
    suspend fun searchMovies(query: String): PagedEntity<MovieEntity>?
    suspend fun loadPopularMovies(page: Int): PagedEntity<MovieEntity>?
    suspend fun getMovieDetails(moviesId: Int): MovieDetailsEntity?
}