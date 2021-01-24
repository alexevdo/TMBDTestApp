package com.sano.tmdbtestapp.domain

import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity

interface IMovieRepository {
    suspend fun searchMovies(query: String): PagedEntity<MovieEntity>?
    suspend fun loadPopularMovies(page: Int): PagedEntity<MovieEntity>?
    suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity?
    suspend fun saveMovies(movies: List<MovieEntity>)
    suspend fun findSavedMoviesByName(query: String): List<MovieEntity>
    suspend fun loadSavedMovieDetails(movieId: Int): MovieDetailsEntity
    suspend fun saveMovieDetails(movieDetails: MovieDetailsEntity)
    suspend fun loadSavedPopularMovies(): List<MovieEntity>
    suspend fun savePopularMovies(popularMovieIds: List<Int>)
    suspend fun clearSavedPopularMovies()
}