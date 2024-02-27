package com.ninestudios.local.news.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ninestudios.local.news.entities.PopularNewsEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PopularNewsEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @ColumnInfo(name = "num_results") val numResults: Int,
    val results:List<NewsEntity>,
    val status:String

){ companion object{
        const val TABLE_NAME = "popular_news"
    }
}