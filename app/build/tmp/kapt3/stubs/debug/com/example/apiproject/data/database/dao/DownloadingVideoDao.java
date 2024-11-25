package com.example.apiproject.data.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H\'J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bH\'J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0005H\'JZ\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\'J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0005H\'J\u0018\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\'\u00a8\u0006\u0019"}, d2 = {"Lcom/example/apiproject/data/database/dao/DownloadingVideoDao;", "", "changePauseState", "", "name", "", "isPaused", "", "deleteDownloadingVideo", "path", "getAllDownloadingVideos", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/apiproject/data/database/entity/DownloadingVideo;", "getAudioDownloadedStatus", "audioPath", "insertDownloadingVideo", "url", "cookie", "image", "title", "audio", "updateAudioDownloadedStatus", "updateError", "error", "VideoDownloader (3) - 1.3_debug"})
@androidx.annotation.Keep()
@androidx.room.Dao()
public abstract interface DownloadingVideoDao {
    
    @androidx.room.Query(value = "Insert into DownloadingVideos (name, url, path, cookie, isPaused ,title,image,audio,audioPath) VALUES (:name, :url, :path, :cookie, :isPaused ,:title,:image,:audio,:audioPath)")
    public abstract void insertDownloadingVideo(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String path, @org.jetbrains.annotations.Nullable()
    java.lang.String cookie, boolean isPaused, @org.jetbrains.annotations.Nullable()
    java.lang.String image, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String audio, @org.jetbrains.annotations.Nullable()
    java.lang.String audioPath);
    
    @androidx.room.Query(value = "DELETE FROM DownloadingVideos WHERE path = :path")
    public abstract void deleteDownloadingVideo(@org.jetbrains.annotations.NotNull()
    java.lang.String path);
    
    @androidx.room.Query(value = "UPDATE DownloadingVideos SET status = :error WHERE name = :name")
    public abstract void updateError(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String error);
    
    @androidx.room.Query(value = "UPDATE DownloadingVideos SET isAudioDownloaded = true WHERE audioPath = :audioPath")
    public abstract void updateAudioDownloadedStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String audioPath);
    
    @androidx.room.Query(value = "SELECT isAudioDownloaded FROM DownloadingVideos WHERE audioPath = :audioPath")
    public abstract boolean getAudioDownloadedStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String audioPath);
    
    @androidx.room.Query(value = "UPDATE DownloadingVideos SET isPaused = :isPaused WHERE name = :name")
    public abstract void changePauseState(@org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean isPaused);
    
    @androidx.room.Query(value = "SELECT * FROM DownloadingVideos")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.apiproject.data.database.entity.DownloadingVideo>> getAllDownloadingVideos();
}