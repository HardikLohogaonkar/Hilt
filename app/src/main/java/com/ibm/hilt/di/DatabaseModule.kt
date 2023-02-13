package com.ibm.hilt.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibm.hilt.database.AppDao
import com.ibm.hilt.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext mApplicationContext: Context): AppDatabase {
        return Room.databaseBuilder(mApplicationContext, AppDatabase::class.java, "Movie")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideAppDao(mAppDatabase: AppDatabase): AppDao {
        return mAppDatabase.mAppDao
    }
}