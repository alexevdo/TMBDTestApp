package com.sano.tmdbtestapp.data

import com.sano.tmdbtestapp.domain.IMovieRepository
import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity

class MovieRepository(private val networkDataSource: INetworkDataSource): IMovieRepository {

    private val modelMapper: ModelMapper = ModelMapper()

    override suspend fun searchMovies(query: String): PagedEntity<MovieEntity>? {
        return networkDataSource.searchMovies(query)?.let {
            modelMapper.pagedResponseToEntity(it)
        }
    }

    override suspend fun loadPopularMovies(page: Int): PagedEntity<MovieEntity>? {
        return networkDataSource.discoverMovies(page)?.let {
            modelMapper.pagedResponseToEntity(it)
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity? {
        return networkDataSource.getMovieDetails(movieId)?.let {
            modelMapper.movieDetailsModelToEntity(it)
        }
    }
}