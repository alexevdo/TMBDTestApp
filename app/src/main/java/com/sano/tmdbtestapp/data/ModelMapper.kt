package com.sano.tmdbtestapp.data

import com.sano.tmdbtestapp.data.db.entity.MovieDbEntity
import com.sano.tmdbtestapp.data.db.entity.MovieDetailsDbEntity
import com.sano.tmdbtestapp.data.network.pojo.MovieDetailsModel
import com.sano.tmdbtestapp.data.network.pojo.MovieModel
import com.sano.tmdbtestapp.data.network.pojo.PagedResponse
import com.sano.tmdbtestapp.domain.entity.MovieDetailsEntity
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity
import java.text.SimpleDateFormat
import java.util.*

class ModelMapper {

    // "2013-08-30"
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun pagedResponseNetworkToDomain(pagedResponse: PagedResponse): PagedEntity<MovieEntity> {
        val movieEntities = pagedResponse.results?.map { movieNetworkToDomain(it) }
        return PagedEntity(
            movieEntities,
            pagedResponse.page,
            pagedResponse.totalResults,
            pagedResponse.totalPages
        )
    }

    private fun movieNetworkToDomain(movieModel: MovieModel): MovieEntity {
        return MovieEntity(movieModel.id, movieModel.title, movieModel.posterPath)
    }

    fun movieDetailsNetworkToDomain(movieDetailsModel: MovieDetailsModel): MovieDetailsEntity {
        return MovieDetailsEntity(
            movieDetailsModel.id,
            movieDetailsModel.title,
            movieDetailsModel.overview,
            movieDetailsModel.releaseDate?.let { dateFormat.parse(it) },
            movieDetailsModel.posterPath
        )
    }

    fun movieDomainToDb(movie: MovieEntity): MovieDbEntity {
        return MovieDbEntity(movie.id, movie.title, movie.posterImagePath)
    }

    fun movieDbToDomain(movieDbEntity: MovieDbEntity): MovieEntity {
        return MovieEntity(movieDbEntity.id, movieDbEntity.title, movieDbEntity.posterPath)
    }

    fun movieDetailsDbToDomain(movieDetails: MovieDetailsDbEntity): MovieDetailsEntity {
        return MovieDetailsEntity(
            movieDetails.id,
            movieDetails.title,
            movieDetails.overview,
            movieDetails.releaseDate?.let { dateFormat.parse(it) },
            movieDetails.posterPath
        )
    }

    fun movieDetailsDomainToDb(movieDetails: MovieDetailsEntity): MovieDetailsDbEntity {
        return MovieDetailsDbEntity(
            movieDetails.id,
            movieDetails.title,
            movieDetails.posterImagePath,
            movieDetails.releaseDate ?.let { dateFormat.format(it) },
            movieDetails.overview
        )
    }


}