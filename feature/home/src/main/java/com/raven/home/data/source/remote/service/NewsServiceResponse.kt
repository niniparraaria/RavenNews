package com.raven.home.data.source.remote.service


import com.google.gson.annotations.SerializedName
import com.ninestudios.local.news.entities.NewsEntity
import com.ninestudios.local.news.entities.NewsMedia
import com.ninestudios.local.news.entities.NewsMediaList
import com.ninestudios.local.news.entities.NewsMediaMetadata
import com.ninestudios.local.news.entities.NewsMediaMetadataList
import com.ninestudios.local.news.entities.PopularNewsEntity

data class NewsServiceResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("status")
    val status: String
) {
    data class Result(
        @SerializedName("abstract")
        val abstract: String,
        @SerializedName("adx_keywords")
        val adxKeywords: String,
        @SerializedName("asset_id")
        val assetId: Long,
        @SerializedName("byline")
        val byline: String,
        @SerializedName("column")
        val column: Any?,
        @SerializedName("des_facet")
        val desFacet: List<String>,
        @SerializedName("eta_id")
        val etaId: Int,
        @SerializedName("geo_facet")
        val geoFacet: List<String>,
        @SerializedName("id")
        val id: Long,
        @SerializedName("media")
        val mediaList: List<Media>,
        @SerializedName("nytdsection")
        val nytdsection: String,
        @SerializedName("org_facet")
        val orgFacet: List<String>,
        @SerializedName("per_facet")
        val perFacet: List<String>,
        @SerializedName("published_date")
        val publishedDate: String,
        @SerializedName("section")
        val section: String,
        @SerializedName("source")
        val source: String,
        @SerializedName("subsection")
        val subsection: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("updated")
        val updated: String,
        @SerializedName("uri")
        val uri: String,
        @SerializedName("url")
        val url: String
    ) {
        data class Media(
            @SerializedName("approved_for_syndication")
            val approvedForSyndication: Int,
            @SerializedName("caption")
            val caption: String,
            @SerializedName("copyright")
            val copyright: String,
            @SerializedName("media-metadata")
            val mediaMetadata: List<MediaMetadata>,
            @SerializedName("subtype")
            val subtype: String,
            @SerializedName("type")
            val type: String
        ) {
            data class MediaMetadata(
                @SerializedName("format")
                val format: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("url")
                val url: String,
                @SerializedName("width")
                val width: Int
            ){
                fun toNewsMediaMediadata():NewsMediaMetadata{
                    return NewsMediaMetadata(format, url, height, width)
                }
            }
            fun toNewsMedia():NewsMedia{
                return NewsMedia(
                    type = type,
                    caption = type,
                    mediaMetadata = NewsMediaMetadataList(metadaList = mediaMetadata.map { it.toNewsMediaMediadata() })
                )
            }
        }
        fun toNewsEntity():NewsEntity {
            return NewsEntity(
                id = id,
                title = title,
                type = type,
                url = url,
                content = abstract,
                byline = byline,
                publishDate = publishedDate,
                section = section,
                source = source,
                media = NewsMediaList(mediaList.map { it.toNewsMedia() })
            )
        }
    }
    fun toPopularNewsEntity():PopularNewsEntity{
        return PopularNewsEntity(
            results = results?.map { it.toNewsEntity() }?: emptyList(),
            numResults = numResults,
            status = status
        )
    }
}