package com.ibm.hilt.repository

import com.ibm.hilt.model.GetTrendingMovieResponse
import com.ibm.hilt.network.NetworkApiConstants.TRENDING
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppRepository {

    @GET("$TRENDING/{movie}/{week}")
    suspend fun getTrendingMovies(
        @Path("movie") type: String,
        @Path("week") duration: String,
        @Query("api_key") apiKey: String
    ): Response<GetTrendingMovieResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    )
}