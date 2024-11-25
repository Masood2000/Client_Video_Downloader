package com.example.apiproject.data.database.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Query
import com.example.apiproject.data.database.entity.DownloadingVideo
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface DownloadingVideoDao {
    @Query("Insert into DownloadingVideos (name, url, path, cookie, isPaused ,title,image,audio,audioPath) VALUES (:name, :url, :path, :cookie, :isPaused ,:title,:image,:audio,:audioPath)")
    fun insertDownloadingVideo(name: String, url: String, path: String, cookie: String?, isPaused: Boolean, image: String?, title: String?, audio: String?, audioPath: String?)
    @Query("DELETE FROM DownloadingVideos WHERE path = :path")
    fun deleteDownloadingVideo(path: String)

    //update Error
    @Query("UPDATE DownloadingVideos SET status = :error WHERE name = :name")
    fun updateError(name: String, error: String)
    //update audio path
    @Query("UPDATE DownloadingVideos SET isAudioDownloaded = true WHERE audioPath = :audioPath")
    fun updateAudioDownloadedStatus(audioPath: String )

    //get audio downloaded status
    @Query("SELECT isAudioDownloaded FROM DownloadingVideos WHERE audioPath = :audioPath")
    fun getAudioDownloadedStatus(audioPath: String): Boolean
    //change pause state
    @Query ("UPDATE DownloadingVideos SET isPaused = :isPaused WHERE name = :name")
    fun changePauseState(name: String, isPaused: Boolean)

    //get all downloading videos
    @Query("SELECT * FROM DownloadingVideos")
    fun getAllDownloadingVideos(): Flow<List<DownloadingVideo>>


}