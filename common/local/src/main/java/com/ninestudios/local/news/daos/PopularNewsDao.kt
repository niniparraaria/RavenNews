package com.ninestudios.local.news.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ninestudios.local.news.entities.PopularNewsEntity

@Dao
interface PopularNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularNews(popularNews:PopularNewsEntity)

    @Query("SELECT * FROM ${PopularNewsEntity.TABLE_NAME}")
    suspend fun getPopularNews(): PopularNewsEntity
}