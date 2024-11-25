package com.example.apiproject.domain.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apiproject.data.database.dao.DownloadedVideoDao
import com.example.apiproject.data.database.dao.DownloadingVideoDao
import com.example.apiproject.data.database.entity.DownloadedVideo
import com.example.apiproject.data.database.entity.DownloadingVideo

@Database(entities = [DownloadingVideo::class , DownloadedVideo::class],version=1)
abstract class AppDb : RoomDatabase() {
    abstract fun downloadingVideoDao(): DownloadingVideoDao
    abstract fun downloadedVideoDao(): DownloadedVideoDao
}