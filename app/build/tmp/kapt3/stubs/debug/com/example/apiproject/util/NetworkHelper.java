package com.example.apiproject.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tJ\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cJ>\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010 2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010 JL\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010#2\u0006\u0010\u001e\u001a\u00020\t2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010 2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010 R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n \u0005*\u0004\u0018\u00010\u000f0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006$"}, d2 = {"Lcom/example/apiproject/util/NetworkHelper;", "", "()V", "apiService", "Lcom/example/apiproject/data/interfaces/DataAPI;", "kotlin.jvm.PlatformType", "getApiService", "()Lcom/example/apiproject/data/interfaces/DataAPI;", "cookie", "", "getCookie", "()Ljava/lang/String;", "setCookie", "(Ljava/lang/String;)V", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "decodeHtmlEntities", "input", "getData", "Lcom/google/gson/JsonObject;", "encodedUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "internetIsConnected", "", "isInternetConnectionAvailable", "context", "Landroid/content/Context;", "sendGetRequest", "url", "params", "", "headers", "sendGetRequestResponseWithCookie", "", "VideoDownloader (4) - 1.4_debug"})
public final class NetworkHelper {
    @org.jetbrains.annotations.Nullable()
    private static java.lang.String cookie;
    private static final retrofit2.Retrofit retrofit = null;
    private static final com.example.apiproject.data.interfaces.DataAPI apiService = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.util.NetworkHelper INSTANCE = null;
    
    private NetworkHelper() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCookie() {
        return null;
    }
    
    public final void setCookie(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    public final com.example.apiproject.data.interfaces.DataAPI getApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getData(@org.jetbrains.annotations.NotNull()
    java.lang.String encodedUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.google.gson.JsonObject> $completion) {
        return null;
    }
    
    public final boolean internetIsConnected() {
        return false;
    }
    
    public final boolean isInternetConnectionAvailable(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String decodeHtmlEntities(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String sendGetRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> params, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> headers) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Map<java.lang.String, java.lang.String> sendGetRequestResponseWithCookie(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> params, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> headers) {
        return null;
    }
}