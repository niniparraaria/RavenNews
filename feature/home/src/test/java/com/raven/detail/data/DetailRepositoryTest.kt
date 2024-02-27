package com.raven.detail.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ninestudios.local.news.daos.NewsDao
import com.ninestudios.local.news.entities.NewsEntity
import com.ninestudios.local.news.entities.NewsMedia
import com.ninestudios.local.news.entities.NewsMediaList
import com.ninestudios.local.news.entities.NewsMediaMetadata
import com.ninestudios.local.news.entities.NewsMediaMetadataList
import com.raven.home.CoroutineTestRule
import com.raven.home.data.source.local.toNewsDataResult
import com.raven.home.data.source.remote.service.NewsServiceResponse
import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var newsDao: NewsDao

    private lateinit var repository: DetailRepository
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = DetailRepository(newsDao)
    }

    @Test
    fun `get new result data`() = runTest {
        val mediaMetadata = NewsMediaMetadata("format", "url", 100, 100)
        val mediaMetadataList = NewsMediaMetadataList(listOf(mediaMetadata))
        val media = NewsMedia("type","caption", mediaMetadataList )
        val mediaList = NewsMediaList(listOf(media))
        val results = NewsEntity(1, "title", "type","url","content", "byline", "date", "section", "source",mediaList)
        Mockito.`when`(newsDao.getNewsItem(Mockito.anyLong())).thenReturn(results)
        val response = newsDao.getNewsItem(Mockito.anyLong())
        Mockito.verify(newsDao).getNewsItem(Mockito.anyLong())
        Assert.assertEquals(results, response)
    }

    @Test
    fun `get new item then throw`() = runTest {
       Mockito.`when`(newsDao.getNewsItem(Mockito.anyLong())).thenThrow(NullPointerException())
        val response = repository.getNewItem(Mockito.anyLong())
        response.collect{ result ->
            assert(result.status == NetworkStatus.ERROR)
        }
    }

}