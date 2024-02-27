package com.raven.home.domain.usecases

import com.raven.core.bases.BaseUseCase
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val dataSource: HomeDataSource,
) : BaseUseCase<String, NetworkResult<List<NewsData.Result>>>() {

    override fun execute(params: String): Flow<NetworkResult<List<NewsData.Result>>> {
        return dataSource.getNews(params)
    }
}
