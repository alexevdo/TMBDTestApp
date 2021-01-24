package com.sano.tmdbtestapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sano.tmdbtestapp.data.db.entity.PopularMoviesDbEntity

@Dao
interface PopularMoviesDao {

    @Query("SELECT * FROM popularmoviesdbentity")
    suspend fun getAll(): List<PopularMoviesDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PopularMoviesDbEntity>)

    @Query("DELETE FROM popularmoviesdbentity")
    suspend fun clear()

}