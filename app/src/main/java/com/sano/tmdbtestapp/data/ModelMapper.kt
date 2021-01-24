package com.sano.tmdbtestapp.data

import com.sano.tmdbtestapp.data.pojo.MovieDetailsModel
import com.sano.tmdbtestapp.data.pojo.MovieModel
import com.sano.tmdbtestapp.data.pojo.PagedResponse
import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity
import java.text.SimpleDateFormat
import java.util.*

class ModelMapper {

    // "2013-08-30"
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun pagedResponseToEntity(pagedResponse: PagedResponse): PagedEntity<MovieEntity> {
        val movieEntities = pagedResponse.results?.map { movieModelToMovieEntity(it) }
        return PagedEntity(
            movieEntities,
            pagedResponse.page,
            pagedResponse.totalResults,
            pagedResponse.totalPages
        )
    }

    private fun movieModelToMovieEntity(movieModel: MovieModel): MovieEntity {
        return MovieEntity(movieModel.id, movieModel.title, movieModel.posterPath)
    }

    fun movieDetailsModelToEntity(movieDetailsModel: MovieDetailsModel): MovieDetailsEntity {
        return MovieDetailsEntity(
            movieDetailsModel.id,
            movieDetailsModel.title,
            movieDetailsModel.overview,
            movieDetailsModel.releaseDate?.let { dateFormat.parse(it) },
            movieDetailsModel.posterPath
        )
    }
}