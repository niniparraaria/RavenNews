package com.raven.home.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.raven.home.CoroutineTestRule
import com.raven.home.data.HomeRepository
import com.raven.home.domain.models.NewsData
import com.raven.home.domain.models.NewsState
import com.raven.network.NetworkResult
import com.raven.network.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeViewModelTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var mockRepository: HomeRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `news results with 200 response`() {
        runTest {
            val mediaMetadata = NewsData.Result.Media.MediaMetadata("format", 100,"url", 100)
            val media = NewsData.Result.Media(listOf(mediaMetadata), "type")
            val results = NewsData.Result("abstract", "byline", 1, listOf(media), "date","section", "source", "title", "type", "url")
            val data = listOf(results)
            val networkResultExpected = NetworkResult.Success(data)
            val stateExpected = NewsState(NetworkStatus.SUCCESS, data = data)
            val flow = flow{
                emit(networkResultExpected)
            }
            val stateInitExpected = NewsState()
            BDDMockito.given(mockRepository.getNews(Mockito.anyString())).willReturn(flow)
            viewModel = HomeViewModel(mockRepository)
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
            Mockito.verify(mockRepository).getNews(Mockito.anyString())
        }
    }

    @Test
    fun `news results with error`() {
        runTest {
            val errorMessage = "Error Message For You"
            val exception = IllegalStateException(errorMessage)
            val networkResultExpected = NetworkResult.Error<List<NewsData.Result>>(exception.toString())
            val stateExpected = NewsState(NetworkStatus.ERROR, errorMessage = exception.toString())
            val flow = flow{
                emit(networkResultExpected)
            }
            val stateInitExpected = NewsState()
            BDDMockito.given(mockRepository.getNews(Mockito.anyString())).willReturn(flow)
            viewModel = HomeViewModel(mockRepository)
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
            Mockito.verify(mockRepository).getNews(Mockito.anyString())
        }
    }
    @Test
    fun `news results with runtime error`() {
        runTest {
            val errorMessage = "404"
            val exception = RuntimeException(errorMessage)
            val networkResultExpected = NetworkResult.Error<List<NewsData.Result>>(exception.toString())
            val stateExpected = NewsState(NetworkStatus.ERROR, errorMessage = exception.toString())
            val flow = flow{
                emit(networkResultExpected)
            }
            val stateInitExpected = NewsState()
            BDDMockito.given(mockRepository.getNews(Mockito.anyString())).willReturn(flow)
            viewModel = HomeViewModel(mockRepository)
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
            Mockito.verify(mockRepository).getNews(Mockito.anyString())
        }
    }
}