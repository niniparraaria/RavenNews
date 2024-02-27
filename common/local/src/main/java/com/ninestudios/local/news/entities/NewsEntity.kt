package com.ninestudios.local.news.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ninestudios.local.news.entities.NewsEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NewsEntity (
    @PrimaryKey val id:Long,
    val title:String,
    val type:String,
    val url:String,
    val content:String,
    val byline:String,
    @ColumnInfo(name = "published_date") val publishDate: String,
    val section:String,
    val source:String,
    val media: NewsMediaList
){
    companion object{
        const val TABLE_NAME = "news"
    }

}