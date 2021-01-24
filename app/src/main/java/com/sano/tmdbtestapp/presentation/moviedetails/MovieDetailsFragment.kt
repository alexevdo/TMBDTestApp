package com.sano.tmdbtestapp.presentation.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.sano.tmdbtestapp.R
import com.sano.tmdbtestapp.presentation.BaseFragment
import com.sano.tmdbtestapp.presentation.Utils
import java.text.SimpleDateFormat
import java.util.*

private const val MOVIE_ID_ARG = "MOVIE_ID_ARG"

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()

    // April 11, 2012
    private val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)

    private lateinit var title: TextView
    private lateinit var poster: ImageView
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = view.findViewById(R.id.title)
        poster = view.findViewById(R.id.poster)
        releaseDate = view.findViewById(R.id.releaseDate)
        overview = view.findViewById(R.id.overview)

        viewModel.stateLiveData.observe(viewLifecycleOwner) { render(it) }

        arguments?.getInt(MOVIE_ID_ARG)?.let {
            viewModel.intent(MovieDetailsIntent.FetchMovie(it))
        } ?: let {
            Log.d(MovieDetailsFragment::class.java.simpleName, "Movie id is empty")
            parentFragmentManager.popBackStack()
        }
    }

    private fun render(state: MovieDetailsState) {
        when (state) {
            MovieDetailsState.LoadingState -> progressBar?.visibility = View.VISIBLE
            is MovieDetailsState.DataState -> renderDataState(state)
            is MovieDetailsState.ErrorState -> renderErrorState(state)
        }
    }

    private fun renderDataState(dataState: MovieDetailsState.DataState) {
        progressBar?.visibility = View.GONE
        title.text = dataState.data.title
        view?.let {
            Glide.with(it)
                .load(Utils.posterUrl(dataState.data.posterImagePath))
                .into(poster)
        }
        overview.text = dataState.data.overview
        releaseDate.text = dataState.data.releaseDate?.let { dateFormat.format(it) }
    }

    private fun renderErrorState(errorState: MovieDetailsState.ErrorState) {
        progressBar?.visibility = View.GONE
        Toast.makeText(context, getString(R.string.smth_went_wrong), Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(movieId: Int): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID_ARG, movieId)
                }
            }
        }
    }
}