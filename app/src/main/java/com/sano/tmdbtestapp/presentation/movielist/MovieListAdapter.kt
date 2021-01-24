package com.sano.tmdbtestapp.presentation.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sano.tmdbtestapp.BuildConfig
import com.sano.tmdbtestapp.R
import com.sano.tmdbtestapp.domain.entity.MovieEntity

private const val POSTER_WIDTH = "w780"

class MovieListAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList: List<MovieEntity> = emptyList()

    fun setItems(items: List<MovieEntity>) {
        movieList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = movieList[position].title
        Glide.with(holder.itemView)
            .load(BuildConfig.API_IMAGE_URL + POSTER_WIDTH + movieList[position].posterImagePath)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.poster)
}