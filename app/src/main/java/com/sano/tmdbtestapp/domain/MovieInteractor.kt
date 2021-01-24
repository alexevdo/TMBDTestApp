package com.sano.tmdbtestapp.domain

import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity

class MovieInteractor(private val repository: IMovieRepository): IMovieInteractor {

    override suspend fun searchMovies(query: String): PagedEntity<MovieEntity>? {
        return repository.searchMovies(query)
    }

    override suspend fun loadPopularMovies(page: Int): PagedEntity<MovieEntity>? {
        return repository.loadPopularMovies(page)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity? {
        return repository.getMovieDetails(movieId)
    }

}