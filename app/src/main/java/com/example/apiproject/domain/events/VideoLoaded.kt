package com.example.apiproject.domain.events

import com.example.apiproject.data.database.entity.DownloadedVideo

sealed class VideoLoaded {
    data class Success(val data: List<DownloadedVideo>) : VideoLoaded()
    data class Error(val error: String) : VideoLoaded()
    object Loading : VideoLoaded()
    object Empty : VideoLoaded()
}