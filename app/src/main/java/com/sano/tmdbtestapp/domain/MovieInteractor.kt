package com.sano.tmdbtestapp.domain

import android.util.Log
import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity
import java.lang.Exception

class MovieInteractor(private val repository: IMovieRepository): IMovieInteractor {

    override suspend fun searchMovies(query: String): PagedEntity<MovieEntity>? {
        return try {
            repository.searchMovies(query)?.also { result ->
                result.values?.let {
                    repository.saveMovies(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(MovieInteractor::class.simpleName, "Error while searchMovies, trying db")
            val dbResult = repository.findSavedMoviesByName(query)
            PagedEntity(dbResult, 1, dbResult.size, 1)
        }
    }

    override suspend fun loadPopularMovies(page: Int, isInitialRequest: Boolean): PagedEntity<MovieEntity>? {
        return try {
            repository.loadPopularMovies(page)?.also { result ->
                if (isInitialRequest) {
                    repository.clearSavedPopularMovies()
                }

                result.values?.let { movieEntities ->
                    repository.saveMovies(movieEntities)
                    repository.savePopularMovies(movieEntities.map { it.id })
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(MovieInteractor::class.simpleName, "Error while loadPopularMovies, trying db")
            if(isInitialRequest) {
                val dbResult = repository.loadSavedPopularMovies()
                PagedEntity(dbResult, 1, dbResult.size, 1)
            } else null
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity? {
        return try {
            repository.getMovieDetails(movieId)?.also { result ->
                repository.saveMovieDetails(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(MovieInteractor::class.simpleName, "Error while getMovieDetails, trying db")
            repository.loadSavedMovieDetails(movieId)
        }
    }

}