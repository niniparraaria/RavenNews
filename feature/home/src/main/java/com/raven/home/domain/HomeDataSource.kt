package com.raven.home.domain

import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {

    fun getNews(period:String): Flow<NetworkResult<List<NewsData.Result>>>
}
