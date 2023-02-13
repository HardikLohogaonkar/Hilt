package com.ibm.hilt.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.hilt.database.AppDatabase
import com.ibm.hilt.model.GetTrendingMovieResponse
import com.ibm.hilt.model.Movie
import com.ibm.hilt.network.NetworkApiConstants.API_KEY
import com.ibm.hilt.repository.AppRepositoryImpl
import com.ibm.hilt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val mAppRepositoryImpl: AppRepositoryImpl,
    private val mAppDatabase: AppDatabase
) :
    ViewModel() {

    //    private val mMovieListData by lazy { MutableLiveData<Resource<Response<GetTrendingMovieResponse>>>() }
    private val mMovieListData by lazy { MutableLiveData<Resource<List<Movie>>>() }
    val mMovieList: LiveData<Resource<List<Movie>>>
        get() = mMovieListData

    fun getMovieList() {
        mMovieListData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            mAppRepositoryImpl.getTrendingMovies("movie", "week", API_KEY)
        }
    }
    fun fetchMovieList(){
        val mMovieData = mAppDatabase.mAppDao.getAllTrendingMovies()
        Log.d("Hardik", "getMovieList: ${mMovieData}")

        mMovieData.let {
            when {
                it.isNullOrEmpty() -> {

                }
                it.isNotEmpty() -> {
                    mMovieListData.postValue(Resource.Success(it))
                }
            }
        }
    }
}