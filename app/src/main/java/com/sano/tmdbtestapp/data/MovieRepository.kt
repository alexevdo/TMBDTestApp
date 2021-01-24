package com.sano.tmdbtestapp.data

import com.sano.tmdbtestapp.data.db.DbDataSource
import com.sano.tmdbtestapp.data.db.entity.PopularMoviesDbEntity
import com.sano.tmdbtestapp.data.network.INetworkDataSource
import com.sano.tmdbtestapp.domain.IMovieRepository
import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity

class MovieRepository(
    private val networkDataSource: INetworkDataSource,
    private val dbDataSource: DbDataSource
) : IMovieRepository {

    private val modelMapper: ModelMapper = ModelMapper()

    override suspend fun searchMovies(query: String): PagedEntity<MovieEntity>? {
        return networkDataSource.searchMovies(query)?.let {
            modelMapper.pagedResponseNetworkToDomain(it)
        }
    }

    override suspend fun loadPopularMovies(page: Int): PagedEntity<MovieEntity>? {
        return networkDataSource.discoverMovies(page)?.let {
            modelMapper.pagedResponseNetworkToDomain(it)
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity? {
        return networkDataSource.getMovieDetails(movieId)?.let {
            modelMapper.movieDetailsNetworkToDomain(it)
        }
    }

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        dbDataSource.insertMovies(movies.map { modelMapper.movieDomainToDb(it) })
    }

    override suspend fun findSavedMoviesByName(query: String): List<MovieEntity> {
        return dbDataSource.findMoviesByName(query).map { modelMapper.movieDbToDomain(it) }
    }

    override suspend fun loadSavedMovieDetails(movieId: Int): MovieDetailsEntity {
        return modelMapper.movieDetailsDbToDomain(dbDataSource.getMovieDetails(movieId))
    }

    override suspend fun saveMovieDetails(movieDetails: MovieDetailsEntity) {
        dbDataSource.insertMovieDetails(modelMapper.movieDetailsDomainToDb(movieDetails))
    }

    override suspend fun loadSavedPopularMovies(): List<MovieEntity> {
        val popularMovieIds = dbDataSource.getPopularMovies().map { it.id }
        return dbDataSource.loadMoviesByIds(popularMovieIds).map { modelMapper.movieDbToDomain(it) }
    }

    override suspend fun savePopularMovies(popularMovieIds: List<Int>) {
        return dbDataSource.insertPopularMovies(popularMovieIds.map { PopularMoviesDbEntity(it) })
    }

    override suspend fun clearSavedPopularMovies() {
        dbDataSource.clearPopularMovies()
    }

}