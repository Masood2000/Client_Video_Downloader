package com.example.apiproject.data.database.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Query
import com.example.apiproject.data.database.entity.DownloadedVideo
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface DownloadedVideoDao {

    @Query("Insert or Ignore into DownloadedVideos (name, path,downloadUrl,image,title,audioPath) VALUES (:name, :path, :downloadedurl, :image, :title, :audioPath)")
    fun insertDownloadedVideo(name: String, path: String, downloadedurl: String?, image: String?, title: String?, audioPath: String?)

    @Query("Select * from DownloadedVideos")
    fun getAllDownloadedVideos(): Flow<List<DownloadedVideo>>

    @Query("Select count(*) from DownloadedVideos where name=:givenName ")
    fun getCount(givenName: String):Int

    @Query ("UPDATE DownloadedVideos SET name = :newName , path=:newPath WHERE path = :path")
    fun updateDownload(path:String,newName:String,newPath:String)


    @Query("Delete from DownloadedVideos where path=:path ")
    fun deleteVideo(path:String)




}