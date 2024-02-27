package com.raven.home.domain.models

import com.raven.network.NetworkStatus


data class NewsData(
    val results: List<Result>,
) {
    data class Result(
        val abstract: String,
        val byline: String,
        val id: Long,
        val media: List<Media>,
        val publishedDate: String,
        val section: String,
        val source: String,
        val title: String,
        val type: String,
        val url: String
    ) {
        data class Media(
            val mediaMetadata: List<MediaMetadata>,
            val type: String
        ) {
            data class MediaMetadata(
                val format: String,
                val height: Int,
                val url: String,
                val width: Int
            )
        }
    }
}

data class NewsState(val status:NetworkStatus? = null, val data: List<NewsData.Result>? = null ,val errorMessage:String? = null)