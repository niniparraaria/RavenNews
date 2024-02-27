package com.ninestudios.local.news.entities

data class NewsMedia (
    val type:String,
    val caption:String,
    val mediaMetadata: NewsMediaMetadataList,
)
data class NewsMediaList(
    val newMediaList: List<NewsMedia>
)