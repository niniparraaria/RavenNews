package com.raven.detail.data

import com.ninestudios.local.news.daos.NewsDao
import com.raven.detail.domain.DetailDataSource
import com.raven.home.data.source.local.toNewsDataResult
import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val newsDao: NewsDao
) : DetailDataSource {

    override fun getNewItem(id: Long): Flow<NetworkResult<NewsData.Result>> {
       return flow<NetworkResult<NewsData.Result>> {
           val result = NetworkResult.Success(newsDao.getNewsItem(id).toNewsDataResult())
           emit(result)
       }.catch { throwable ->
           val error = NetworkResult.Error<NewsData.Result>(message = throwable.message?:"")
           emit(error)
       }
    }
}
