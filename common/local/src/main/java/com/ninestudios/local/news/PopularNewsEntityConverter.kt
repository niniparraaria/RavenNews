package com.ninestudios.local.news

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ninestudios.local.news.entities.NewsEntity
import com.ninestudios.local.news.entities.NewsMediaList
import com.ninestudios.local.news.entities.NewsMediaMetadataList

class PopularNewsEntityConverter {

    @TypeConverter
    fun fromStringToNewsList(value:String):NewsMediaList?{
        val listType = object : TypeToken<NewsMediaList>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromNewsListToString(dataList: NewsMediaList):String{
        val listType = object : TypeToken<NewsMediaList>() {}.type
        return Gson().toJson(dataList, listType)
    }

    @TypeConverter
    fun fromStringToNewsMetadataList(value:String):NewsMediaMetadataList?{
        val listType = object : TypeToken<NewsMediaMetadataList>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromNewsMetadataListToString(dataList: NewsMediaMetadataList):String{
        val listType = object : TypeToken<NewsMediaMetadataList>() {}.type
        return Gson().toJson(dataList, listType)
    }

    @TypeConverter
    fun fromStringToNewsEntityList(value:String):List<NewsEntity>?{
        val listType = object : TypeToken<List<NewsEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromNewsEntityListToString(dataList: List<NewsEntity>):String{
        val listType = object : TypeToken<List<NewsEntity>>() {}.type
        return Gson().toJson(dataList, listType)
    }
}