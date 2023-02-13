package com.ibm.hilt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ibm.hilt.model.GetMovieResponse
import com.ibm.hilt.model.Movie

@Database(entities = [Movie::class/*, GetMovieResponse::class*/], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val mAppDao: AppDao
}