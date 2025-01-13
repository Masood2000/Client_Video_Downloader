package com.example.apiproject.domain.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/example/apiproject/domain/db/AppDb;", "Landroidx/room/RoomDatabase;", "()V", "downloadedVideoDao", "Lcom/example/apiproject/data/database/dao/DownloadedVideoDao;", "downloadingVideoDao", "Lcom/example/apiproject/data/database/dao/DownloadingVideoDao;", "VideoDownloader (4) - 1.4_debug"})
@androidx.room.Database(entities = {com.example.apiproject.data.database.entity.DownloadingVideo.class, com.example.apiproject.data.database.entity.DownloadedVideo.class}, version = 1)
public abstract class AppDb extends androidx.room.RoomDatabase {
    
    public AppDb() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.apiproject.data.database.dao.DownloadingVideoDao downloadingVideoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.apiproject.data.database.dao.DownloadedVideoDao downloadedVideoDao();
}