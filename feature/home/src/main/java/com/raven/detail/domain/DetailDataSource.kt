package com.raven.detail.domain

import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DetailDataSource {
    fun getNewItem(id:Long): Flow<NetworkResult<NewsData.Result>>
}
