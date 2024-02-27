package com.ninestudios.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ninestudios.local.news.PopularNewsEntityConverter
import com.ninestudios.local.news.daos.NewsDao
import com.ninestudios.local.news.daos.PopularNewsDao
import com.ninestudios.local.news.entities.NewsEntity
import com.ninestudios.local.news.entities.PopularNewsEntity

@Database(
    entities = [NewsEntity::class, PopularNewsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PopularNewsEntityConverter::class)
abstract class RavenDatabase: RoomDatabase() {
    abstract fun getNewsDao():NewsDao

    abstract fun getPopularNewsDao():PopularNewsDao

    companion object {
        @Volatile
        private var INSTANCE: RavenDatabase? = null

        fun getDatabase(context: Context): RavenDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RavenDatabase::class.java,
                    "raven_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}