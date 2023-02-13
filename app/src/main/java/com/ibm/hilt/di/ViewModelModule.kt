package com.ibm.hilt.di

import com.ibm.hilt.repository.AppRepository
import com.ibm.hilt.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(mRepositoryImpl: AppRepositoryImpl): AppRepository
}