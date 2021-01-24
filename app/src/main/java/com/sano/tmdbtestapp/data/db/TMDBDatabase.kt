package com.sano.tmdbtestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sano.tmdbtestapp.data.db.dao.MovieDao
import com.sano.tmdbtestapp.data.db.dao.MovieDetailsDao
import com.sano.tmdbtestapp.data.db.dao.PopularMoviesDao
import com.sano.tmdbtestapp.data.db.entity.MovieDbEntity
import com.sano.tmdbtestapp.data.db.entity.MovieDetailsDbEntity
import com.sano.tmdbtestapp.data.db.entity.PopularMoviesDbEntity

@Database(entities = [MovieDbEntity::class, MovieDetailsDbEntity::class, PopularMoviesDbEntity::class], version = 1)
abstract class TMDBDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun popularMoviesDbDao(): PopularMoviesDao
}