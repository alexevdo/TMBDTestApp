package com.sano.tmdbtestapp.data.db

import com.sano.tmdbtestapp.data.db.entity.MovieDbEntity
import com.sano.tmdbtestapp.data.db.entity.MovieDetailsDbEntity
import com.sano.tmdbtestapp.data.db.entity.PopularMoviesDbEntity

interface IDbDataSource {
    suspend fun insertMovies(movies: List<MovieDbEntity>)
    suspend fun loadMoviesByIds(movieIds: List<Int>): List<MovieDbEntity>
    suspend fun findMoviesByName(query: String): List<MovieDbEntity>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDbEntity
    suspend fun insertMovieDetails(movieDetails: MovieDetailsDbEntity)
    suspend fun getPopularMovies(): List<PopularMoviesDbEntity>
    suspend fun insertPopularMovies(popularMovies: List<PopularMoviesDbEntity>)
    suspend fun clearPopularMovies()
}