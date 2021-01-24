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
import com.sano.tmdbtestapp.presentation.Utils

class MovieListAdapter(private val clickListener: MovieEntityClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList: List<MovieEntity> = emptyList()

    private val itemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(position: Int) {
            clickListener.onMovieEntityClick(movieList[position])
        }
    }

    fun setItems(items: List<MovieEntity>) {
        movieList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)

        return MovieViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = movieList[position].title
        Glide.with(holder.itemView)
            .load(Utils.posterUrl(movieList[position].posterImagePath))
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MovieViewHolder(itemView: View, private val clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.poster)

    init {
        itemView.setOnClickListener { clickListener.onItemClick(adapterPosition) }
    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

interface MovieEntityClickListener {
    fun onMovieEntityClick(movieEntity: MovieEntity)
}