package com.raven.home.di

import com.ninestudios.local.news.daos.NewsDao
import com.ninestudios.local.news.daos.PopularNewsDao
import com.raven.home.data.HomeRepository
import com.raven.home.data.source.remote.service.HomeService
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.mapper.GetNewsMapper
import com.raven.home.domain.usecases.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Provides
    @Singleton
    fun provideHomeRepository(service: HomeService, newsDao: NewsDao, popularNewsDao: PopularNewsDao, mapper: GetNewsMapper): HomeRepository = HomeRepository(service, newsDao, popularNewsDao, mapper)

    @Provides
    @Singleton
    fun provideGetNewsUseCase(homeDataSource: HomeDataSource): GetNewsUseCase = GetNewsUseCase(homeDataSource)

    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService = retrofit.create(HomeService::class.java)

}
