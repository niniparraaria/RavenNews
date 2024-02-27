package com.raven.detail.di

import com.ninestudios.local.news.daos.NewsDao
import com.raven.detail.data.DetailRepository
import com.raven.detail.domain.DetailDataSource
import com.raven.detail.domain.usecases.GetNewItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetailModule {
    @Provides
    @Singleton
    fun provideDetailRepository(newsDao: NewsDao): DetailRepository = DetailRepository(newsDao)

    @Provides
    @Singleton
    fun provideGetNewItemUseCase(dataSource: DetailDataSource): GetNewItemUseCase = GetNewItemUseCase(dataSource)

}
