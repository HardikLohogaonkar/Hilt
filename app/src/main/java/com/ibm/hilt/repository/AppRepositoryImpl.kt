package com.ibm.hilt.repository

import com.ibm.hilt.database.AppDatabase
import com.ibm.hilt.model.GetTrendingMovieResponse
import com.ibm.hilt.network.NetworkApiClient
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val mNetworkApiClient: NetworkApiClient,
    private val mAppDatabase: AppDatabase
) : BaseRepository() {

    override suspend fun getTrendingMovies(
        type: String,
        duration: String,
        apiKey: String
    ): Response<GetTrendingMovieResponse> {

        var mResponse =
            mNetworkApiClient.getRetrofitClient().getTrendingMovies(type, duration, apiKey)

        mResponse.body()?.let { mAppDatabase.mAppDao.insertAll(it.results) }

//        mResponse.let {
//            it.body()?.let {
//                var mResult = handleSuccess(it)
//            }
//            it.errorBody()?.let {
//                if (it is HttpException){
//                    it.response()?.code()?.let { error ->
//                        var mResult = handleException(error)
//                    }
//                }
//            }
//        }
        if (mResponse.isSuccessful){

        }else{

        }
        return mResponse
    }

    override suspend fun getMovieDetails(movieId: Int, apiKey: String, language: String) {
        return mNetworkApiClient.getRetrofitClient().getMovieDetails(movieId, apiKey, language)
    }
}