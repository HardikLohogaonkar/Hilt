package com.ibm.hilt.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ibm.hilt.databinding.ActivityMainBinding
import com.ibm.hilt.model.GetMovieResponse
import com.ibm.hilt.model.GetTrendingMovieResponse
import com.ibm.hilt.model.Movie
import com.ibm.hilt.ui.adapter.MovieListAdapter
import com.ibm.hilt.util.Resource
import com.ibm.hilt.util.isInternetAvailable
import com.ibm.hilt.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Response

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mMovieViewModel: MovieViewModel
    private lateinit var mMovieListAdapter: MovieListAdapter
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private var mMovieList = arrayListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)

        mMovieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        mMovieListAdapter = MovieListAdapter()

        if (isInternetAvailable(this)){
            mMovieViewModel.getMovieList()
        }else{
            mMovieViewModel.fetchMovieList()
        }
        mMovieViewModel.mMovieList.observe(this, movieObserver)
    }

    private val movieObserver = Observer<Resource<List<Movie>>> { response ->
        when (response) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                if (mMovieList.isNotEmpty())
                    mMovieList.clear()

                if (response.data != null) {
                    var mResponse = response.data

                    mResponse.let { movie ->
                        mMovieList.addAll(movie)
                        mMovieListAdapter.submitList(mMovieList)
                        mActivityMainBinding.rvMovieList.adapter = mMovieListAdapter
                    }
                }
            }
            is Resource.Error -> {

            }
        }
    }
}