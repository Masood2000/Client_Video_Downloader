package com.example.apiproject.domain.extractor.types;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR*\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lcom/example/apiproject/domain/extractor/types/DailyMotionUtils;", "", "()V", "onDone", "Lcom/example/apiproject/domain/extractor/types/DailyMotionUtils$OnDone;", "getOnDone", "()Lcom/example/apiproject/domain/extractor/types/DailyMotionUtils$OnDone;", "setOnDone", "(Lcom/example/apiproject/domain/extractor/types/DailyMotionUtils$OnDone;)V", "xModels", "Ljava/util/ArrayList;", "Lcom/example/apiproject/domain/extractor/types/XModel;", "Lkotlin/collections/ArrayList;", "getXModels", "()Ljava/util/ArrayList;", "setXModels", "(Ljava/util/ArrayList;)V", "fetch", "", "response", "", "getJson", "html", "query", "key", "string", "showResult", "Companion", "OnDone", "VideoDownloader (4) - 1.4_debug"})
@android.annotation.SuppressLint(value = {"StaticFieldLeak"})
public final class DailyMotionUtils {
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.example.apiproject.domain.extractor.types.XModel> xModels;
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.domain.extractor.types.DailyMotionUtils.OnDone onDone;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.domain.extractor.types.DailyMotionUtils.Companion Companion = null;
    
    public DailyMotionUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.example.apiproject.domain.extractor.types.XModel> getXModels() {
        return null;
    }
    
    public final void setXModels(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.apiproject.domain.extractor.types.XModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.apiproject.domain.extractor.types.DailyMotionUtils.OnDone getOnDone() {
        return null;
    }
    
    public final void setOnDone(@org.jetbrains.annotations.Nullable()
    com.example.apiproject.domain.extractor.types.DailyMotionUtils.OnDone p0) {
    }
    
    public final void fetch(@org.jetbrains.annotations.NotNull()
    java.lang.String response, @org.jetbrains.annotations.Nullable()
    com.example.apiproject.domain.extractor.types.DailyMotionUtils.OnDone onDone) {
    }
    
    private final void showResult() {
    }
    
    private final java.lang.String query(java.lang.String key, java.lang.String string) {
        return null;
    }
    
    private final java.lang.String getJson(java.lang.String html) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J4\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a8\u0006\u0010"}, d2 = {"Lcom/example/apiproject/domain/extractor/types/DailyMotionUtils$Companion;", "", "()V", "getDailyMotionID", "", "link", "putModel", "", "url", "quality", "model", "Ljava/util/ArrayList;", "Lcom/example/apiproject/domain/extractor/types/XModel;", "Lkotlin/collections/ArrayList;", "removeSlash", "ogay", "VideoDownloader (4) - 1.4_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getDailyMotionID(@org.jetbrains.annotations.Nullable()
        java.lang.String link) {
            return null;
        }
        
        private final void putModel(java.lang.String url, java.lang.String quality, java.util.ArrayList<com.example.apiproject.domain.extractor.types.XModel> model) {
        }
        
        private final java.lang.String removeSlash(java.lang.String ogay) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/example/apiproject/domain/extractor/types/DailyMotionUtils$OnDone;", "", "onResult", "", "xModels", "Ljava/util/ArrayList;", "Lcom/example/apiproject/domain/extractor/types/XModel;", "Lkotlin/collections/ArrayList;", "VideoDownloader (4) - 1.4_debug"})
    public static abstract interface OnDone {
        
        public abstract void onResult(@org.jetbrains.annotations.Nullable()
        java.util.ArrayList<com.example.apiproject.domain.extractor.types.XModel> xModels);
    }
}