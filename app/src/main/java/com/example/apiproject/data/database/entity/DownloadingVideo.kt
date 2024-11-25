package com.example.apiproject.data.database.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "DownloadingVideos")
data class DownloadingVideo(

    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    val name: String,
    val url: String,
    val path: String,
    val cookie: String?,
    val isPaused: Boolean,
    var image: String?=null,
    var title:String?=null,
    var audio:String? = null,
    var audioPath:String? = null,
    var isAudioDownloaded:Boolean? = false,
    var status: String? = "Downloading"
)