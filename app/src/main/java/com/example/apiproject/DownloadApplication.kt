package com.example.apiproject

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.Configuration
import com.liulishuo.filedownloader.FileDownloader
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class DownloadApplication: Application() {
    private val channelId = "DOWNLOAD_CHANNEL"

    override fun onCreate() {
        super.onCreate()
        FileDownloader.setupOnApplicationOnCreate(this)
            .connectionCreator(
                FileDownloadUrlConnection.Creator(
                    FileDownloadUrlConnection.Configuration()
                        .connectTimeout(30000) // set connection timeout.
                        .readTimeout(30000) // set read timeout.
                )
            )
            .commit()
        FileDownloader.getImpl().setMaxNetworkThreadCount(5)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

        createNotificationChannel()
    }


    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            channelId,
            "Download Worker",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)


        val notificationChannel2 = NotificationChannel(
            "CompletedChannel",
            "Download Worker2",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(notificationChannel2)



    }



}