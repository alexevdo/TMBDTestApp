package com.sano.tmdbtestapp.data.db

import android.content.Context
import androidx.room.Room
import com.sano.tmdbtestapp.data.db.entity.MovieDbEntity
import com.sano.tmdbtestapp.data.db.entity.MovieDetailsDbEntity
import com.sano.tmdbtestapp.data.db.entity.PopularMoviesDbEntity

class DbDataSource(applicationContext: Context) : IDbDataSource {

    private val db: TMDBDatabase = Room.databaseBuilder(
        applicationContext,
        TMDBDatabase::class.java, "tmdb-database"
    ).build()

    override suspend fun insertMovies(movies: List<MovieDbEntity>) {
        db.movieDao().insertAll(movies)
    }

    override suspend fun loadMoviesByIds(movieIds: List<Int>): List<MovieDbEntity> {
        return db.movieDao().loadAllByIds(movieIds)
    }

    override suspend fun findMoviesByName(query: String): List<MovieDbEntity> {
        return db.movieDao().findByName(query)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDbEntity {
        return db.movieDetailsDao().getMovieDetails(movieId)
    }

    override suspend fun insertMovieDetails(movieDetails: MovieDetailsDbEntity) {
        db.movieDetailsDao().insertAll(movieDetails)
    }

    override suspend fun getPopularMovies(): List<PopularMoviesDbEntity> {
        return db.popularMoviesDbDao().getAll()
    }

    override suspend fun insertPopularMovies(popularMovies: List<PopularMoviesDbEntity>) {
        db.popularMoviesDbDao().insertAll(popularMovies)
    }

    override suspend fun clearPopularMovies() {
        db.popularMoviesDbDao().clear()
    }
}