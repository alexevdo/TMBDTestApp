package com.sano.tmdbtestapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sano.tmdbtestapp.data.db.entity.MovieDetailsDbEntity

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM moviedetailsdbentity WHERE id IS :movieId LIMIT 1")
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movieDetails: MovieDetailsDbEntity)
}