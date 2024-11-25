package com.example.apiproject.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\nH\u0007J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\nH\u0007\u00a8\u0006\u0010"}, d2 = {"Lcom/example/apiproject/data/di/AppModule;", "", "()V", "getMainActivityViewModel", "Lcom/example/apiproject/domain/viewmodels/MainActivityViewModel;", "downloadingVideoDao", "Lcom/example/apiproject/data/database/dao/DownloadingVideoDao;", "downloadedVideoDao", "Lcom/example/apiproject/data/database/dao/DownloadedVideoDao;", "provideAppDatabase", "Lcom/example/apiproject/domain/db/AppDb;", "appContext", "Landroid/content/Context;", "provideDownloadedVideoDao", "appDatabase", "provideDownloadingDao", "VideoDownloader (3) - 1.3_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.data.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.domain.db.AppDb provideAppDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.database.dao.DownloadingVideoDao provideDownloadingDao(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.domain.db.AppDb appDatabase) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.database.dao.DownloadedVideoDao provideDownloadedVideoDao(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.domain.db.AppDb appDatabase) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.domain.viewmodels.MainActivityViewModel getMainActivityViewModel(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.data.database.dao.DownloadingVideoDao downloadingVideoDao, @org.jetbrains.annotations.NotNull()
    com.example.apiproject.data.database.dao.DownloadedVideoDao downloadedVideoDao) {
        return null;
    }
}