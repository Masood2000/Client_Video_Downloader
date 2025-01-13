package com.example.apiproject.data.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\'J@\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\'J \u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\'\u00a8\u0006\u0016"}, d2 = {"Lcom/example/apiproject/data/database/dao/DownloadedVideoDao;", "", "deleteVideo", "", "path", "", "getAllDownloadedVideos", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/apiproject/data/database/entity/DownloadedVideo;", "getCount", "", "givenName", "insertDownloadedVideo", "name", "downloadedurl", "image", "title", "audioPath", "updateDownload", "newName", "newPath", "VideoDownloader (5) - 1.5_debug"})
@androidx.annotation.Keep()
@androidx.room.Dao()
public abstract interface DownloadedVideoDao {
    
    @androidx.room.Query(value = "Insert or Ignore into DownloadedVideos (name, path,downloadUrl,image,title,audioPath) VALUES (:name, :path, :downloadedurl, :image, :title, :audioPath)")
    public abstract void insertDownloadedVideo(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String path, @org.jetbrains.annotations.Nullable()
    java.lang.String downloadedurl, @org.jetbrains.annotations.Nullable()
    java.lang.String image, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String audioPath);
    
    @androidx.room.Query(value = "Select * from DownloadedVideos")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.apiproject.data.database.entity.DownloadedVideo>> getAllDownloadedVideos();
    
    @androidx.room.Query(value = "Select count(*) from DownloadedVideos where name=:givenName ")
    public abstract int getCount(@org.jetbrains.annotations.NotNull()
    java.lang.String givenName);
    
    @androidx.room.Query(value = "UPDATE DownloadedVideos SET name = :newName , path=:newPath WHERE path = :path")
    public abstract void updateDownload(@org.jetbrains.annotations.NotNull()
    java.lang.String path, @org.jetbrains.annotations.NotNull()
    java.lang.String newName, @org.jetbrains.annotations.NotNull()
    java.lang.String newPath);
    
    @androidx.room.Query(value = "Delete from DownloadedVideos where path=:path ")
    public abstract void deleteVideo(@org.jetbrains.annotations.NotNull()
    java.lang.String path);
}