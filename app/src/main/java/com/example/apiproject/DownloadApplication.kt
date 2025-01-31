package com.example.apiproject

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.Configuration
import com.example.apiproject.core.ads.admob.AppOpenManager
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import com.liulishuo.filedownloader.FileDownloader
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class DownloadApplication: Application() {

    private var appOpenManager: AppOpenManager? = null

    private val channelId = "DOWNLOAD_CHANNEL"

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()

        //admob
        MobileAds.initialize(this)

        Log.d("F_TAG", "onCreate: ${FirebaseApp.getInstance()}")

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





        appOpenManager = AppOpenManager(this@DownloadApplication)

        appOpenManager?.fetchAd()

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