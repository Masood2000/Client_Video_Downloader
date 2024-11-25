package com.example.apiproject.data.di

import android.content.Context
import androidx.room.Room
import com.example.apiproject.data.database.dao.DownloadedVideoDao
import com.example.apiproject.data.database.dao.DownloadingVideoDao
import com.example.apiproject.domain.db.AppDb
import com.example.apiproject.domain.viewmodels.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDb {
        return Room.databaseBuilder(
            appContext,
            AppDb::class.java,
            "download_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDownloadingDao(appDatabase: AppDb): DownloadingVideoDao {
        return appDatabase.downloadingVideoDao()
    }

    @Singleton
    @Provides
    fun provideDownloadedVideoDao(appDatabase: AppDb): DownloadedVideoDao {
        return appDatabase.downloadedVideoDao()
    }

    @Provides
    fun getMainActivityViewModel(downloadingVideoDao: DownloadingVideoDao,downloadedVideoDao: DownloadedVideoDao):MainActivityViewModel{
        return MainActivityViewModel(downloadingVideoDao,downloadedVideoDao)
    }


}