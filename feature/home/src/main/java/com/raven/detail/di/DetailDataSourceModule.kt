package com.raven.detail.di

import com.raven.detail.data.DetailRepository
import com.raven.detail.domain.DetailDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailDataSourceModule {

    @Binds
    abstract fun bindDetailDataSource(repository: DetailRepository): DetailDataSource
}
