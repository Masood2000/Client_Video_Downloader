package com.example.apiproject.domain.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.apiproject.data.database.dao.DownloadedVideoDao
import com.example.apiproject.data.database.dao.DownloadingVideoDao
import com.example.apiproject.domain.events.DownloadingVideos
import com.example.apiproject.domain.events.VideoLoaded
import com.example.apiproject.domain.workmanager.DownloadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import java.time.Duration

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    var downloadingVideoDao: DownloadingVideoDao,
    var downloadedVideoDao: DownloadedVideoDao
) : ViewModel() {

    private var _videoLoaded: MutableStateFlow<VideoLoaded> = MutableStateFlow(VideoLoaded.Empty)

    var downloadedVideos: StateFlow<VideoLoaded> = _videoLoaded.asStateFlow()


    private var _videoDownloading: MutableStateFlow<DownloadingVideos> =
        MutableStateFlow(DownloadingVideos.Empty)

    var downloadingVideos: StateFlow<DownloadingVideos> = _videoDownloading.asStateFlow()


    fun deleteDownloadingVideo(path: String) {
        Log.d("MainActivityViewModel", "deleteDownloadingVideo: ")
        downloadingVideoDao.deleteDownloadingVideo(path)
    }

    fun getDownloadedVideos() {
        Log.d("MainActivityViewModel", "getDownloadedVideos: ")
        var flow = downloadedVideoDao.getAllDownloadedVideos()
        flow.onEach {
            _videoLoaded.value = VideoLoaded.Success(it.reversed())
        }.launchIn(viewModelScope)
    }

    fun getDownloadingVideos() {
        Log.d("MainActivityViewModel", "getDownloading videos: ")

        var flow = downloadingVideoDao.getAllDownloadingVideos()
        flow.onEach {
            _videoDownloading.value = DownloadingVideos.Success(it.reversed())
        }.launchIn(viewModelScope)
    }

    fun deleteDownloadedVideo(path: String) {
        Log.d("MainActivityViewModel", "deleteDownloadedVideo: path: $path")
        viewModelScope.launch(Dispatchers.IO) {
            downloadedVideoDao.deleteVideo(path)
        }
    }

    fun updateDownloadedVideo(path: String, newName: String, newPath: String) =
        downloadedVideoDao.updateDownload(path, newName, newPath)

    fun getCount(givenName: String) = downloadedVideoDao.getCount(givenName)
    fun insertDownloadedVideo(
        name: String,
        path: String,
        downloadedurl: String?,
        image: String?,
        title: String?,
        audioPath: String?
    ) = downloadedVideoDao.insertDownloadedVideo(name, path, downloadedurl, image, title, audioPath)

    fun insertDownloadingVideo(
        name: String,
        url: String,
        path: String,
        cookie: String?,
        isPaused: Boolean,
        image: String?,
        title: String?,
        audio: String?,
        audioPath: String?
    ) = downloadingVideoDao.insertDownloadingVideo(
        name,
        url,
        path,
        cookie,
        isPaused,
        image,
        title,
        audio,
        audioPath
    )

    fun downloadVideo(
        context: Context,
        name: String?,
        url: String,
        path: String,
        cookie: String?,
        isPaused: Boolean,
        image: String?,
        title: String?
    ) {
        insertDownloadingVideo(
            (name ?: UUID.randomUUID()).toString(),
            url,
            path,
            cookie,
            isPaused,
            image,
            title,
            null,
            null
        )

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<DownloadWorker>()
                .setInitialDelay(Duration.ZERO) // Start immediately
                .setInputData(
                    Data.Builder()
                        .putString("url", url)
                        .putString("cookie", cookie)
                        .putString("image", image)
                        .putString("path", path)
                        .putString("title", title)
                        .putString("audio", null)
                        .build()
                )

                .build()

        WorkManager
            .getInstance(context)
            .enqueue(uploadWorkRequest)

    }

    fun downloadAudio(
        context: Context,
        name: String?,
        url: String,
        cookie: String?,
        isPaused: Boolean,
        image: String?,
        title: String?,
        audio: String,
        audioPath: String
    ) {
        insertDownloadingVideo(
            (name ?: UUID.randomUUID()).toString(),
            url,
            audioPath,
            cookie,
            isPaused,
            image,
            title,
            audio,
            audioPath
        )
        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<DownloadWorker>()
                .setInitialDelay(Duration.ZERO) // Start immediately
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setInputData(
                    Data.Builder()
                        .putString("url", url)
                        .putString("path", audioPath)
                        .putString("cookie", cookie)
                        .putString("image", image)
                        .putString("title", title)
                        .putString("audio", audioPath)
                        .build()
                )
                .build()

        WorkManager
            .getInstance(context)
            .enqueue(uploadWorkRequest)
    }


}

