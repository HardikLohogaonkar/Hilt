package com.ibm.hilt.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
data class GetTrendingMovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable

@Parcelize
@Entity(tableName = "movie")
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable