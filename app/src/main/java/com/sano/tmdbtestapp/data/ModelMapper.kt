package com.sano.tmdbtestapp.data

import com.sano.tmdbtestapp.data.pojo.MovieModel
import com.sano.tmdbtestapp.data.pojo.PagedResponse
import com.sano.tmdbtestapp.domain.entity.MovieEntity
import com.sano.tmdbtestapp.domain.entity.PagedEntity

class ModelMapper {
    fun pagedResponseToPagedEntity(pagedResponse: PagedResponse): PagedEntity<MovieEntity> {
        val movieEntities = pagedResponse.results?.map { movieModelToMovieEntity(it) }
        return PagedEntity(movieEntities, pagedResponse.page, pagedResponse.totalResults, pagedResponse.totalPages)
    }

    private fun movieModelToMovieEntity(movieModel: MovieModel): MovieEntity {
        return MovieEntity(movieModel.id, movieModel.title, movieModel.posterPath)
    }
}