package com.ninestudios.local.news.entities

data class NewsMediaMetadata (
    val format:String,
    val url:String,
    val height:Int,
    val width: Int
)

data class NewsMediaMetadataList(
    val metadaList: List<NewsMediaMetadata>
)