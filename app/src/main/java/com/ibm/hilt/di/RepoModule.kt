package com.ibm.hilt.di

import com.ibm.hilt.network.NetworkApiClient
import com.ibm.hilt.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideWebService() = NetworkApiClient


//    @Provides
//    @Singleton
//    fun provideApiService(mNetworkApiClient: NetworkApiClient): AppRepository =
//        mNetworkApiClient.getRetrofitClient().create(AppRepository::class.java)

}