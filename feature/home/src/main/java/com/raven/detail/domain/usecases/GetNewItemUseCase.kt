package com.raven.detail.domain.usecases

import com.raven.core.bases.BaseUseCase
import com.raven.detail.domain.DetailDataSource
import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewItemUseCase @Inject constructor(
    private val dataSource: DetailDataSource,
) : BaseUseCase<Long, NetworkResult<NewsData.Result>>() {

    override fun execute(params:Long): Flow<NetworkResult<NewsData.Result>> {
        return dataSource.getNewItem(params)
    }
}
