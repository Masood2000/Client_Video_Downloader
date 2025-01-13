package com.example.apiproject.domain.extractor.types;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b0\n2\u0006\u0010\f\u001a\u00020\u0004J \u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/example/apiproject/domain/extractor/types/TikTokExtractor;", "Lcom/example/apiproject/domain/extractor/Extractor;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "getVideoDataMap", "", "", "data", "getVideoLink", "Lcom/example/apiproject/data/api/ExtractedData;", "context", "Landroid/content/Context;", "url", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "VideoDownloader (4) - 1.4_debug"})
public final class TikTokExtractor implements com.example.apiproject.domain.extractor.Extractor {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String TAG = "Tiktok Extractor";
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy<?> instance$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private static java.lang.String cookie;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.domain.extractor.types.TikTokExtractor.Companion Companion = null;
    
    public TikTokExtractor() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTAG() {
        return null;
    }
    
    public final void setTAG(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getVideoLink(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.apiproject.data.api.ExtractedData> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.util.List<java.lang.String>> getVideoDataMap(@org.jetbrains.annotations.NotNull()
    java.lang.String data) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/example/apiproject/domain/extractor/types/TikTokExtractor$Companion;", "", "()V", "cookie", "", "getCookie", "()Ljava/lang/String;", "setCookie", "(Ljava/lang/String;)V", "instance", "Lcom/example/apiproject/domain/extractor/types/TikTokExtractor;", "getInstance", "()Lcom/example/apiproject/domain/extractor/types/TikTokExtractor;", "instance$delegate", "Lkotlin/Lazy;", "VideoDownloader (4) - 1.4_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.apiproject.domain.extractor.types.TikTokExtractor getInstance() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getCookie() {
            return null;
        }
        
        public final void setCookie(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
    }
}