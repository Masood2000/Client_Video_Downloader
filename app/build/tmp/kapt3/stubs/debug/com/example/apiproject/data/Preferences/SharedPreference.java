package com.example.apiproject.data.Preferences;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/apiproject/data/Preferences/SharedPreference;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mPrefEditor", "Landroid/content/SharedPreferences$Editor;", "mPreference", "Landroid/content/SharedPreferences;", "getLastAutoFetchLink", "", "setLastAutoFetchLink", "", "link", "Companion", "VideoDownloader (4) - 1.4_debug"})
public final class SharedPreference {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences mPreference = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences.Editor mPrefEditor = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String APP_PREFS_NAME = "OneTapDownloaderPreference";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LAST_AUTO_FETCH_LINK = "lastlink";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.data.Preferences.SharedPreference.Companion Companion = null;
    
    public SharedPreference(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void setLastAutoFetchLink(@org.jetbrains.annotations.NotNull()
    java.lang.String link) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastAutoFetchLink() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/apiproject/data/Preferences/SharedPreference$Companion;", "", "()V", "APP_PREFS_NAME", "", "LAST_AUTO_FETCH_LINK", "VideoDownloader (4) - 1.4_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}