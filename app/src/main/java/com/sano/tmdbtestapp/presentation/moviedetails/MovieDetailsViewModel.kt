package com.sano.tmdbtestapp.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sano.tmdbtestapp.data.MovieRepository
import com.sano.tmdbtestapp.data.NetworkDataSource
import com.sano.tmdbtestapp.domain.IMovieInteractor
import com.sano.tmdbtestapp.domain.MovieInteractor
import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.presentation.movielist.MovieListState
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailsViewModel: ViewModel() {

    private val interactor: IMovieInteractor = MovieInteractor(MovieRepository(NetworkDataSource()))
    private var dataState: MovieListState.DataState? = null

    private val mutableStateLiveData: MutableLiveData<MovieDetailsState> = MutableLiveData()
    val stateLiveData: LiveData<MovieDetailsState> = mutableStateLiveData

    fun intent(intent: MovieDetailsIntent) {
        when(intent) {
            is MovieDetailsIntent.FetchMovie -> {
                mutableStateLiveData.value = MovieDetailsState.LoadingState
                viewModelScope.launch {
                    try {
                        interactor.getMovieDetails(intent.movieId)?.let {
                            mutableStateLiveData.value = MovieDetailsState.DataState(it)
                        }
                    } catch (e: Exception) {
                        mutableStateLiveData.value = MovieDetailsState.ErrorState(e)
                    }
                }
            }
        }
    }
}

sealed class MovieDetailsState {
    object LoadingState : MovieDetailsState()
    data class DataState(
        val data: MovieDetailsEntity
    ) : MovieDetailsState()

    data class ErrorState(val error: Throwable) : MovieDetailsState()
}

sealed class MovieDetailsIntent {
    data class FetchMovie(val movieId: Int) : MovieDetailsIntent()
}