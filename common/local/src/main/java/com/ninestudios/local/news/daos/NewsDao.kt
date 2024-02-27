package com.ninestudios.local.news.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ninestudios.local.news.entities.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(news:List<NewsEntity>)

    @Query("SELECT * FROM ${NewsEntity.TABLE_NAME}")
    suspend fun getNewsList(): List<NewsEntity>

    @Query("SELECT * FROM ${NewsEntity.TABLE_NAME} WHERE id=:id")
    suspend fun getNewsItem(id:Long): NewsEntity
}