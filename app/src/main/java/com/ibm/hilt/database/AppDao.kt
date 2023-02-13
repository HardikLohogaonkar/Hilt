package com.ibm.hilt.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibm.hilt.model.Movie

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movie: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getAllTrendingMovies(): List<Movie>
}