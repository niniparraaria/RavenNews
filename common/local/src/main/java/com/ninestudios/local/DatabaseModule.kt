package com.ninestudios.local

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = RavenDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun providePopularNewsDao(database: RavenDatabase) =
        database.getPopularNewsDao()

    @Singleton
    @Provides
    fun provideNewsDao(database: RavenDatabase) =
        database.getNewsDao()
}