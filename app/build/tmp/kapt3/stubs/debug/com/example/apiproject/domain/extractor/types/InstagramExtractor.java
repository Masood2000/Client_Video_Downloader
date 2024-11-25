package com.example.apiproject.domain.extractor.types;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0096@\u00a2\u0006\u0002\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0010"}, d2 = {"Lcom/example/apiproject/domain/extractor/types/InstagramExtractor;", "Lcom/example/apiproject/domain/extractor/Extractor;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "getVideoLink", "Lcom/example/apiproject/data/api/ExtractedData;", "context", "Landroid/content/Context;", "link", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "VideoDownloader (3) - 1.3_debug"})
public final class InstagramExtractor implements com.example.apiproject.domain.extractor.Extractor {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String TAG = "InstagramExtractor";
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy<?> instance$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.domain.extractor.types.InstagramExtractor.Companion Companion = null;
    
    public InstagramExtractor() {
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
    java.lang.String link, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.apiproject.data.api.ExtractedData> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/example/apiproject/domain/extractor/types/InstagramExtractor$Companion;", "", "()V", "instance", "Lcom/example/apiproject/domain/extractor/types/InstagramExtractor;", "getInstance", "()Lcom/example/apiproject/domain/extractor/types/InstagramExtractor;", "instance$delegate", "Lkotlin/Lazy;", "VideoDownloader (3) - 1.3_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.apiproject.domain.extractor.types.InstagramExtractor getInstance() {
            return null;
        }
    }
}