package com.raven.home.domain.mapper

import com.raven.home.data.source.remote.service.NewsServiceResponse
import com.raven.home.domain.models.NewsData
import javax.inject.Inject

class GetNewsMapper @Inject constructor() {

     fun transformDomainToUI(params: List<NewsServiceResponse.Result>):List<NewsData.Result>  {
       return params.map { it.toNewsDataResult() }
    }
    fun NewsServiceResponse.Result.toNewsDataResult():NewsData.Result{
        return NewsData.Result(
            id = id,
            abstract = abstract,
            byline = byline,
            publishedDate = publishedDate,
            source = source,
            section = section,
            title = title,
            type = type,
            url = url,
            media = mediaList.map { it.toNewsDataResultMedia() }
        )
    }
    private fun NewsServiceResponse.Result.Media.toNewsDataResultMedia():NewsData.Result.Media{
        return NewsData.Result.Media(
            type = type,
            mediaMetadata = mediaMetadata.map { it.toNewsDataMetadata() }
        )
    }
    private fun NewsServiceResponse.Result.Media.MediaMetadata.toNewsDataMetadata():NewsData.Result.Media.MediaMetadata{
        return NewsData.Result.Media.MediaMetadata(
            format, height, url, width
        )
    }
}
