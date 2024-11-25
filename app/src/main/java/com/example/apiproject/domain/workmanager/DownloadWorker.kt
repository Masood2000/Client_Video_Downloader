package com.example.apiproject.domain.workmanager

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.R
import com.example.apiproject.data.database.dao.DownloadingVideoDao
import com.example.apiproject.data.di.AppModule

import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.UUID
import java.util.concurrent.CountDownLatch


class DownloadWorker  constructor(
    appContext: Context,
   workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams)
{
    private val TAG = "DownloadWorker"
    private val channelId = "DOWNLOAD_CHANNEL"
    private val notificationId = 1


    var downloadingVideoDao: DownloadingVideoDao =
        AppModule.provideAppDatabase(appContext).downloadingVideoDao()
    var downloadedVideoDao = AppModule.provideAppDatabase(appContext).downloadedVideoDao()
    private fun downloadVideo(
        url2: String,
        path:String,
        cookie: String?,
        image: String?,
        title: String?,
        audio: String?
    ) {
        val downloadLatch = CountDownLatch(1) // Create a latch with a count of 1
        try {
            Log.d(TAG, "Download VideoAudio: $audio")
            var url = url2.replace("\"", "")
            //generate map of video url and null  values
            var map = mutableMapOf<String, String?>()
            Log.d(TAG, "url: $url")
            Log.d(TAG, "onViewCreated: $url")
            map.put("url", url)
            map.put("cookie", cookie)
            fun downloadVideo(context: Context, urls: Map<String, String?>, audio: String? = null) {
                // path of the file to be downloaded
                var name = "video${UUID.randomUUID()}.mp4"

                var audioFilePath = if (audio != null) {
                    "Temp_audio${UUID.randomUUID()}.aac"
                } else {
                    null
                }
                var listener = object : FileDownloadListener() {
                    override fun pending(
                        task: BaseDownloadTask?,
                        soFarBytes: Int,
                        totalBytes: Int
                    ) {
                    }

                    override fun progress(
                        task: BaseDownloadTask?,
                        soFarBytes: Int,
                        totalBytes: Int
                    ) {
                        Log.d(TAG, "progress: " + soFarBytes + " / ${totalBytes}")

                    }

                    override fun completed(task: BaseDownloadTask?) {
                        Log.d(TAG, "completed: Video Completed audio: $audio")

                        CoroutineScope(Dispatchers.IO).launch {
                            downloadingVideoDao.deleteDownloadingVideo(path)
                            downloadedVideoDao.insertDownloadedVideo(
                                name,
                                path,
                                url2,
                                image,
                                title,
                                audio
                            )
                            MainActivity.updateData?.update()
                        }

                        downloadLatch.countDown()
                    }

                    override fun paused(
                        task: BaseDownloadTask?,
                        soFarBytes: Int,
                        totalBytes: Int
                    ) {
                        Log.d(TAG, "completed: Video Paused")

                    }

                    override fun error(task: BaseDownloadTask?, e: Throwable?) {
                        Log.d(TAG, "completed: Video Error " + e?.message.toString())
                        CoroutineScope(Dispatchers.IO).launch {
                            downloadingVideoDao.updateError(
                                name,
                                "Error Occurred"
                            )
                            downloadLatch.countDown()

                        }
                        MainActivity.updateData?.update()

                    }

                    override fun warn(task: BaseDownloadTask?) {

                    }

                    override fun started(task: BaseDownloadTask?) {
                        super.started(task)


                    }
                }

                Log.d("Worker Cookie", cookie.toString())
                if (urls["cookie"] != null) {
                    Log.d("DownloadPath", "downloadVideo:" + urls["url"])
                    FileDownloader.getImpl().create(urls["url"]!!)
                        .setPath(path)
                        .addHeader("Cookie", cookie)
                        .addHeader("Accept", "*/*")
                        .addHeader("Accept-Encoding", "identity;q=1, *;q=0")
                        .addHeader("Accept-Language", "en-US,en;q=0.9")
                        .addHeader(
                            "User-Agent",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
                        )
                        .addHeader("Referer", "https://www.tiktok.com/")
                        .addHeader("Postman-Token", "e2fff6b4-9810-4b0c-9eb4-c394824384ba")
                        .addHeader(
                            "Host",
                            urls["url"]?.substring(8, urls["url"]?.indexOf("/", 8) ?: 8)
                        )
                        .setListener(listener)
                        .setAutoRetryTimes(1)
                        .setCallbackProgressTimes(100)
                        .asInQueueTask()
                        .enqueue()
                    FileDownloader.getImpl().start(listener, true)
                }
                else {
                    FileDownloader.getImpl().create(urls["url"]!!)
                        .setPath(path)
                        .setListener(listener)
                        .setAutoRetryTimes(1)
                        .setCallbackProgressTimes(100)
                        .asInQueueTask()
                        .enqueue()
                    var a = FileDownloader.getImpl().start(listener, true);
                    Log.d(TAG, "downloadVideo: id ${a.toString()}")

                }
            }
            downloadVideo(applicationContext, map, audio)
        }
        catch (E: Exception) {

        } catch (e: java.lang.Exception) {

        } catch (e: java.net.SocketException) {

        }
        finally {
            downloadLatch.await() // Wait until the download completes
        }
    }

    override suspend fun doWork(): Result {
        return try {

            setForeground(createForegroundInfo())

            val url = inputData.getString("url")
            val path = inputData.getString("path")
            val cookie = inputData.getString("cookie")
            val image = inputData.getString("image")
            val title = inputData.getString("title")
            val audio = inputData.getString("audio")
            runBlocking {

                downloadVideo(url!!,path?:"", cookie, image, title, audio)
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }


    private fun createForegroundInfo(): ForegroundInfo {
        // Create an intent that will open the app's main activity when the notification is clicked
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // Wrap the intent in a PendingIntent
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        )

        // Create the notification and set the click action
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Downloading...")
            .setContentText("Your video is being downloaded.")
            .setSmallIcon(R.drawable.video_svgrepo_com)
            .setOngoing(true)
            .setContentIntent(pendingIntent)  // Set the PendingIntent here
            .build()

        // Create the ForegroundInfo object
        val foregroundInfo = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            ForegroundInfo(notificationId, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(notificationId, notification)
        }

        return foregroundInfo
    }


    override suspend fun getForegroundInfo(): ForegroundInfo {
        return createForegroundInfo()
    }
}
