package com.sano.tmdbtestapp.presentation

import com.sano.tmdbtestapp.BuildConfig

private const val POSTER_WIDTH = "w780"

object Utils {
    fun posterUrl(posterPath: String?): String? {
        return posterPath?.let {
            BuildConfig.API_IMAGE_URL + POSTER_WIDTH + it
        }
    }
}