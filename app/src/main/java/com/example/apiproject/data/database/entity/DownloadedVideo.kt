package com.example.apiproject.data.database.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Keep
@Entity(tableName = "DownloadedVideos")
data class DownloadedVideo(
    @PrimaryKey
    val path: String,
    val name: String,
    var downloadUrl: String?=null,
    var image: String?=null,
    var title: String?=null,
    var audioPath:String?=null,
    var downloadTime: Long?=null

)
