package com.raven.detail.presentacion.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.ninestudios.local.news.entities.NewsEntity
import com.ninestudios.local.news.entities.NewsMedia
import com.ninestudios.local.news.entities.NewsMediaList
import com.ninestudios.local.news.entities.NewsMediaMetadata
import com.ninestudios.local.news.entities.NewsMediaMetadataList
import com.raven.detail.data.DetailRepository
import com.raven.home.CoroutineTestRule
import com.raven.home.data.HomeRepository
import com.raven.home.data.source.local.toNewsDataResult
import com.raven.home.domain.models.NewsData
import com.raven.home.domain.models.NewsState
import com.raven.home.presentation.viewmodel.HomeViewModel
import com.raven.network.NetworkResult
import com.raven.network.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var mockRepository: DetailRepository

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailViewModel(mockRepository)
    }

    @Test
    fun `new item result ok`() {
        runTest {
            val mediaMetadata = NewsMediaMetadata("format", "url", 100, 100)
            val mediaMetadataList = NewsMediaMetadataList(listOf(mediaMetadata))
            val media = NewsMedia("type","caption", mediaMetadataList )
            val mediaList = NewsMediaList(listOf(media))
            val results = NewsEntity(1, "title", "type","url","content", "byline", "date", "section", "source",mediaList)
            val stateExpected = DetailViewModel.NewsItemState(status = NetworkStatus.SUCCESS, data = results.toNewsDataResult())
            val flow = flow{
                emit(NetworkResult.Success(results.toNewsDataResult()))
            }
            val stateInitExpected = DetailViewModel.NewsItemState()
            BDDMockito.given(mockRepository.getNewItem(Mockito.anyLong())).willReturn(flow)
            viewModel.getNewItem(Mockito.anyLong())
            viewModel.state.test {
                assertEquals(
                    stateInitExpected,
                    awaitItem()
                )
                assertEquals(
                    stateExpected,
                    awaitItem()
                )
            }
            Mockito.verify(mockRepository).getNewItem(Mockito.anyLong())
        }
    }

    @Test
    fun `new item result null`() {
        runTest {
            val stateExpected = DetailViewModel.NewsItemState(status = NetworkStatus.ERROR)
            val flow = flow{
                val error = NetworkResult.Error<NewsData.Result>(message = "error")
                emit(error)
            }
            val stateInitExpected = DetailViewModel.NewsItemState()
            BDDMockito.given(mockRepository.getNewItem(Mockito.anyLong())).willReturn(flow)
            viewModel.getNewItem(Mockito.anyLong())
            viewModel.state.test {
                assertEquals(
                    stateInitExpected,
                    awaitItem()
                )
                assertEquals(
                    stateExpected,
                    awaitItem()
                )
            }
            Mockito.verify(mockRepository).getNewItem(Mockito.anyLong())
        }
    }
}