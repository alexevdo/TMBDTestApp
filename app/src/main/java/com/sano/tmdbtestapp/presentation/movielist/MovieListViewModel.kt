package com.sano.tmdbtestapp.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sano.tmdbtestapp.data.MovieRepository
import com.sano.tmdbtestapp.data.NetworkDataSource
import com.sano.tmdbtestapp.domain.IMovieInteractor
import com.sano.tmdbtestapp.domain.MovieInteractor
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    private val interactor: IMovieInteractor = MovieInteractor(MovieRepository(NetworkDataSource()))
    private var dataState: MovieListState.DataState? = null
    private var searchDataState: MovieListState.DataState? = null

    private val mutableStateLiveData: MutableLiveData<MovieListState> = MutableLiveData()
    val stateLiveData: LiveData<MovieListState> = mutableStateLiveData

    fun intent(intent: MovieListIntent) {
        when (intent) {
            MovieListIntent.LoadPopularMovies -> {
                mutableStateLiveData.value = MovieListState.LoadingState
                viewModelScope.launch {
                    try {
                        val result: PagedEntity<MovieEntity>? = interactor.loadPopularMovies((dataState?.page ?: 0) + 1 )
                        result?.values?.let {
                            reduceDataState(
                                MovieListState.DataState(
                                    it, result.page, result.totalPages, result.totalResults
                                )
                            )
                        }
                    } catch (e: Exception) {
                        mutableStateLiveData.value = MovieListState.ErrorState(e)
                    }
                }
            }
            is MovieListIntent.SearchMovies -> {
                mutableStateLiveData.value = MovieListState.LoadingState
                viewModelScope.launch {
                    try {
                        val result = interactor.searchMovies(intent.query)
                        result?.values?.let {
                            searchDataState = MovieListState.DataState(
                                result.values, result.page, result.totalPages, result.totalResults
                            )
                            mutableStateLiveData.value = searchDataState
                        }
                    } catch (e: Exception) {
                        mutableStateLiveData.value = MovieListState.ErrorState(e)
                    }
                }
            }
            MovieListIntent.ResetDataToPopularMovies -> mutableStateLiveData.value = dataState
        }
    }

    private fun reduceDataState(newState: MovieListState.DataState) {
        dataState = MovieListState.DataState(
        (dataState?.data ?: emptyList()) + newState.data,
            newState.page,
            newState.totalPages,
            newState.totalCount)

        mutableStateLiveData.value = dataState
    }
}

sealed class MovieListState {
    object LoadingState : MovieListState()
    data class DataState(
        val data: List<MovieEntity>,
        val page: Int,
        val totalPages: Int,
        val totalCount: Int
    ) : MovieListState()

    data class ErrorState(val error: Throwable) : MovieListState()
}

sealed class MovieListIntent {
    object LoadPopularMovies : MovieListIntent()
    data class SearchMovies(val query: String) : MovieListIntent()
    object ResetDataToPopularMovies: MovieListIntent()
}