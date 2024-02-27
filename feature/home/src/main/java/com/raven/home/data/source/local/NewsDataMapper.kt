package com.raven.home.data.source.local

import com.ninestudios.local.news.entities.NewsEntity
import com.ninestudios.local.news.entities.NewsMedia
import com.ninestudios.local.news.entities.NewsMediaMetadata
import com.raven.home.domain.models.NewsData


fun NewsEntity.toNewsDataResult():NewsData.Result{
    return NewsData.Result(
        id = id,
        abstract = content,
        byline = byline,
        publishedDate = publishDate,
        source = source,
        section = section,
        title = title,
        type = type,
        url = url,
        media = media.newMediaList.map { it.toNewsDataResultMedia() }
    )
}
fun NewsMedia.toNewsDataResultMedia():NewsData.Result.Media{
    return NewsData.Result.Media(
       type = type,
        mediaMetadata = mediaMetadata.metadaList.map { it.toNewsDataMetadata() }
    )
}
fun NewsMediaMetadata.toNewsDataMetadata():NewsData.Result.Media.MediaMetadata{
    return NewsData.Result.Media.MediaMetadata(
        format, height, url, width
    )
}