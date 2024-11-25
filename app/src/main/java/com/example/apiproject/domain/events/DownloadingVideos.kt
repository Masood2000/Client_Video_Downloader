package com.example.apiproject.domain.events

import com.example.apiproject.data.database.entity.DownloadedVideo
import com.example.apiproject.data.database.entity.DownloadingVideo


sealed class DownloadingVideos {
    data class Success(val data: List<DownloadingVideo>) : DownloadingVideos()
    data class Error(val error: String) : DownloadingVideos()
    object Loading : DownloadingVideos()
    object Empty : DownloadingVideos()
}