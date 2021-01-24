package com.sano.tmdbtestapp.presentation.movielist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sano.tmdbtestapp.R
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.presentation.BaseFragment
import com.sano.tmdbtestapp.presentation.moviedetails.MovieDetailsFragment

class MovieListFragment: BaseFragment(R.layout.fragment_movie_list) {

    private val viewModel: MovieListViewModel by viewModels()

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLiveData.observe(this.viewLifecycleOwner) { render(it) }

        movieListAdapter = MovieListAdapter(object : MovieEntityClickListener {
            override fun onMovieEntityClick(movieEntity: MovieEntity) {
                parentFragmentManager
                    .beginTransaction()
                    .add(R.id.container, MovieDetailsFragment.newInstance(movieEntity.id))
                    .addToBackStack(null)
                    .commit()
            }
        })

        val movieList: RecyclerView = view.findViewById(R.id.movieList)
        movieList.adapter = movieListAdapter

        viewModel.intent(MovieListIntent.LoadPopularMovies)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.options_menu, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnSearchClickListener {
            viewModel.intent(MovieListIntent.SearchMovies(searchView.query.toString()))
        }
    }

    private fun render(state: MovieListState) {
        when(state) {
            MovieListState.LoadingState -> progressBar?.visibility = View.VISIBLE
            is MovieListState.DataState -> renderDataState(state)
            is MovieListState.ErrorState -> renderErrorState(state)
        }
    }

    private fun renderDataState(dataState: MovieListState.DataState) {
        progressBar?.visibility = View.GONE
        movieListAdapter.setItems(dataState.data)
    }

    private fun renderErrorState(errorState: MovieListState.ErrorState) {
        progressBar?.visibility = View.GONE
        Toast.makeText(context, getString(R.string.smth_went_wrong), Toast.LENGTH_LONG).show()
    }
}