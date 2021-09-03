package com.agauchat.android.hackerrank.di

import com.agauchat.android.hackerrank.data.repository.MainRepository
import com.agauchat.android.hackerrank.data.repository.MainRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindMainRepository(mainRepository: MainRepository): MainRepositoryInterface
}
