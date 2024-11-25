package com.example.apiproject.domain.workmanager;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u001bH\u0096@\u00a2\u0006\u0002\u0010\u001cJ@\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\b2\b\u0010\"\u001a\u0004\u0018\u00010\b2\b\u0010#\u001a\u0004\u0018\u00010\b2\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J\u000e\u0010%\u001a\u00020\u0019H\u0096@\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/example/apiproject/domain/workmanager/DownloadWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "TAG", "", "channelId", "downloadedVideoDao", "Lcom/example/apiproject/data/database/dao/DownloadedVideoDao;", "getDownloadedVideoDao", "()Lcom/example/apiproject/data/database/dao/DownloadedVideoDao;", "setDownloadedVideoDao", "(Lcom/example/apiproject/data/database/dao/DownloadedVideoDao;)V", "downloadingVideoDao", "Lcom/example/apiproject/data/database/dao/DownloadingVideoDao;", "getDownloadingVideoDao", "()Lcom/example/apiproject/data/database/dao/DownloadingVideoDao;", "setDownloadingVideoDao", "(Lcom/example/apiproject/data/database/dao/DownloadingVideoDao;)V", "notificationId", "", "createForegroundInfo", "Landroidx/work/ForegroundInfo;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadVideo", "", "url2", "path", "cookie", "image", "title", "audio", "getForegroundInfo", "VideoDownloader (3) - 1.3_debug"})
public final class DownloadWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "DownloadWorker";
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String channelId = "DOWNLOAD_CHANNEL";
    private final int notificationId = 1;
    @org.jetbrains.annotations.NotNull()
    private com.example.apiproject.data.database.dao.DownloadingVideoDao downloadingVideoDao;
    @org.jetbrains.annotations.NotNull()
    private com.example.apiproject.data.database.dao.DownloadedVideoDao downloadedVideoDao;
    
    public DownloadWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParams) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.database.dao.DownloadingVideoDao getDownloadingVideoDao() {
        return null;
    }
    
    public final void setDownloadingVideoDao(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.data.database.dao.DownloadingVideoDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.database.dao.DownloadedVideoDao getDownloadedVideoDao() {
        return null;
    }
    
    public final void setDownloadedVideoDao(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.data.database.dao.DownloadedVideoDao p0) {
    }
    
    private final void downloadVideo(java.lang.String url2, java.lang.String path, java.lang.String cookie, java.lang.String image, java.lang.String title, java.lang.String audio) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    private final androidx.work.ForegroundInfo createForegroundInfo() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getForegroundInfo(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ForegroundInfo> $completion) {
        return null;
    }
}