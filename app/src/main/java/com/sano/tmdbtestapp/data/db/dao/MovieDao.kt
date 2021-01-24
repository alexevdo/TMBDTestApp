package com.sano.tmdbtestapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sano.tmdbtestapp.data.db.entity.MovieDbEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM moviedbentity WHERE id IN (:movieIds)")
    suspend fun loadAllByIds(movieIds: List<Int>): List<MovieDbEntity>

    @Query("SELECT * FROM moviedbentity WHERE title LIKE '%' || :movieName  || '%'")
    suspend fun findByName(movieName: String): List<MovieDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDbEntity>)
}