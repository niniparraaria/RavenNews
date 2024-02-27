package com.raven.home.data

import com.ninestudios.local.news.daos.NewsDao
import com.ninestudios.local.news.daos.PopularNewsDao
import com.raven.home.data.source.remote.service.HomeService
import com.raven.home.data.source.remote.service.NewsServiceResponse
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.mapper.GetNewsMapper
import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeService: HomeService,
    private val newsDao: NewsDao,
    private val popularNewsDao: PopularNewsDao,
    private val mapper: GetNewsMapper
) : HomeDataSource {

    override fun getNews(period:String): Flow<NetworkResult<List<NewsData.Result>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val remoteData = homeService.getNews(period)
            fetchAndInsertData(remoteData)
            emit(NetworkResult.Success(mapper.transformDomainToUI(remoteData.results?: emptyList())))

        }catch (e: HttpException){
            emit(
                NetworkResult.Error(
                    message = "Oops, something went wrong!"
                )
            )
        }catch (e:IOException){
            emit(
                NetworkResult.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }catch (e:Exception){
            emit(
                NetworkResult.Error(
                    message = "Oops, something went wrong!"
                )
            )
        }

    }

    private suspend fun fetchAndInsertData(remoteData: NewsServiceResponse){
        popularNewsDao.insertPopularNews(remoteData.toPopularNewsEntity())
        newsDao.insertNewsList(remoteData.results?.map { it.toNewsEntity() }?: emptyList())
    }
}
