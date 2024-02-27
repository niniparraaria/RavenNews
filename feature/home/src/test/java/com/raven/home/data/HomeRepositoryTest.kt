package com.raven.home.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.ninestudios.local.news.daos.NewsDao
import com.ninestudios.local.news.daos.PopularNewsDao
import com.raven.home.CoroutineTestRule
import com.raven.home.data.source.remote.service.HomeService
import com.raven.home.data.source.remote.service.NewsServiceResponse
import com.raven.home.domain.mapper.GetNewsMapper
import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkResult
import com.raven.network.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var homeService: HomeService

    @Mock
    private lateinit var newsDao: NewsDao

    @Mock
    private lateinit var popularNewsDao: PopularNewsDao

    @Mock
    private lateinit var getNewsMapper: GetNewsMapper


    private lateinit var repository: HomeRepository
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = HomeRepository(homeService, newsDao, popularNewsDao, getNewsMapper)
    }

    @Test
    fun `news results empty`() = runTest {
        val newsServiceResponseExpected = NewsServiceResponse("copy", 1, listOf(), "status")
        Mockito.`when`(homeService.getNews(Mockito.anyString())).thenReturn(newsServiceResponseExpected)
        val response = homeService.getNews(Mockito.anyString())
        Mockito.verify(homeService).getNews(Mockito.anyString())
        assertEquals(newsServiceResponseExpected, response)
    }
    @Test
    fun `news results with data`() {
        runTest {
            val mediaMetadata = NewsServiceResponse.Result.Media.MediaMetadata("format", 100,"url", 100)
            val media = NewsServiceResponse.Result.Media(1, "caaption", "copy", listOf(mediaMetadata), "subtype", "type")
            val results = NewsServiceResponse.Result("abstract", "keywords", 1L, "byline", null, emptyList(),0, emptyList(),1, listOf(media), "nysection", emptyList() ,
                emptyList(), "date","section", "source","subsection", "title","type", "update", "uri","url")
            val newsServiceResponse = NewsServiceResponse("copy", 1, listOf(results), "status")
            Mockito.`when`(homeService.getNews(Mockito.anyString())).thenReturn(newsServiceResponse)
            val response = homeService.getNews(Mockito.anyString())
            Mockito.verify(homeService).getNews(Mockito.anyString())
            assertEquals(newsServiceResponse, response)
        }
    }

    @Test
    fun `news results with data null`() {
        runTest {
            val newsServiceResponse = NewsServiceResponse("copy", 0, null, "status")
            Mockito.`when`(homeService.getNews(Mockito.anyString())).thenReturn(newsServiceResponse)
            val response = homeService.getNews(Mockito.anyString())
            Mockito.verify(homeService).getNews(Mockito.anyString())
            assertEquals(newsServiceResponse, response)
        }
    }

    @Test
    fun `news results with null pointer`() {
        runTest {
            val exception = NullPointerException("error")
            Mockito.`when`(homeService.getNews("1")).thenThrow(exception)
            val response = repository.getNews("1")
            val expectedInit = NetworkResult.Loading<List<NewsData.Result>>()
            val expected = NetworkResult.Error<List<NewsData.Result>>(exception.message!!)
            response.test {
                assert(expectedInit.status == awaitItem().status)
                assert(expected.status == awaitItem().status)
                cancelAndIgnoreRemainingEvents()
            }
            Mockito.verify(homeService).getNews(Mockito.anyString())

        }
    }
}