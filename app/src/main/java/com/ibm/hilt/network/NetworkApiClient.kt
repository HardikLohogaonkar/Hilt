package com.ibm.hilt.network

import com.ibm.hilt.BuildConfig
import com.ibm.hilt.repository.AppRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkApiClient {

//    private var mRetrofitClient: Retrofit? = null

    fun getRetrofitClient(): AppRepository {

        val mHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

//                level = HttpLoggingInterceptor.Level.BODY
            })
            readTimeout(5000, TimeUnit.SECONDS)
            writeTimeout(5000, TimeUnit.SECONDS)
            connectTimeout(5000, TimeUnit.SECONDS)
        }
            .addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .build()

                return@addInterceptor chain.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl(NetworkApiConstants.BASE_URL)
            .client(mHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppRepository::class.java)
    }
}