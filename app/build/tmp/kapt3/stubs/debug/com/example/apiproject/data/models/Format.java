package com.example.apiproject.data.models;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b@\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00bd\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\f\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\f\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u0017\u001a\u00020\f\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\u0006\u0010\u0019\u001a\u00020\u0001\u0012\u0006\u0010\u001a\u001a\u00020\u0004\u0012\u0006\u0010\u001b\u001a\u00020\u0004\u0012\u0006\u0010\u001c\u001a\u00020\f\u00a2\u0006\u0002\u0010\u001dJ\t\u0010:\u001a\u00020\u0001H\u00c6\u0003J\t\u0010;\u001a\u00020\u0004H\u00c6\u0003J\t\u0010<\u001a\u00020\u0004H\u00c6\u0003J\t\u0010=\u001a\u00020\fH\u00c6\u0003J\t\u0010>\u001a\u00020\u0012H\u00c6\u0003J\t\u0010?\u001a\u00020\fH\u00c6\u0003J\t\u0010@\u001a\u00020\u0004H\u00c6\u0003J\t\u0010A\u001a\u00020\fH\u00c6\u0003J\t\u0010B\u001a\u00020\u0004H\u00c6\u0003J\t\u0010C\u001a\u00020\fH\u00c6\u0003J\t\u0010D\u001a\u00020\u0004H\u00c6\u0003J\t\u0010E\u001a\u00020\u0004H\u00c6\u0003J\t\u0010F\u001a\u00020\u0001H\u00c6\u0003J\t\u0010G\u001a\u00020\u0004H\u00c6\u0003J\t\u0010H\u001a\u00020\u0004H\u00c6\u0003J\t\u0010I\u001a\u00020\fH\u00c6\u0003J\t\u0010J\u001a\u00020\u0006H\u00c6\u0003J\t\u0010K\u001a\u00020\u0004H\u00c6\u0003J\t\u0010L\u001a\u00020\u0004H\u00c6\u0003J\t\u0010M\u001a\u00020\u0004H\u00c6\u0003J\t\u0010N\u001a\u00020\u0004H\u00c6\u0003J\t\u0010O\u001a\u00020\fH\u00c6\u0003J\t\u0010P\u001a\u00020\u0004H\u00c6\u0003J\u00ef\u0001\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\fH\u00c6\u0001J\u0013\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010U\u001a\u00020\fH\u00d6\u0001J\t\u0010V\u001a\u00020\u0004H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0011\u0010\b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0011\u0010\t\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0011\u0010\n\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010!R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\r\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u0011\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0011\u0010\u000f\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0011\u0010\u0010\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010)R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0013\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010)R\u0011\u0010\u0014\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010!R\u0011\u0010\u0015\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010)R\u0011\u0010\u0016\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010!R\u0011\u0010\u0017\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010)R\u0011\u0010\u0018\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010!R\u0011\u0010\u0019\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001fR\u0011\u0010\u001a\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010!R\u0011\u0010\u001b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010!R\u0011\u0010\u001c\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010)\u00a8\u0006W"}, d2 = {"Lcom/example/apiproject/data/models/Format;", "", "abr", "acodec", "", "aspect_ratio", "", "audio_ext", "cookies", "dynamic_range", "ext", "filesize", "", "format", "format_id", "format_note", "height", "http_headers", "Lcom/example/apiproject/data/models/HttpHeadersX;", "preference", "protocol", "quality", "resolution", "tbr", "url", "vbr", "vcodec", "video_ext", "width", "(Ljava/lang/Object;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/example/apiproject/data/models/HttpHeadersX;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;I)V", "getAbr", "()Ljava/lang/Object;", "getAcodec", "()Ljava/lang/String;", "getAspect_ratio", "()D", "getAudio_ext", "getCookies", "getDynamic_range", "getExt", "getFilesize", "()I", "getFormat", "getFormat_id", "getFormat_note", "getHeight", "getHttp_headers", "()Lcom/example/apiproject/data/models/HttpHeadersX;", "getPreference", "getProtocol", "getQuality", "getResolution", "getTbr", "getUrl", "getVbr", "getVcodec", "getVideo_ext", "getWidth", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "VideoDownloader (3) - 1.3_debug"})
public final class Format {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Object abr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String acodec = null;
    private final double aspect_ratio = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String audio_ext = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cookies = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dynamic_range = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String ext = null;
    private final int filesize = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String format = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String format_id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String format_note = null;
    private final int height = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.example.apiproject.data.models.HttpHeadersX http_headers = null;
    private final int preference = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String protocol = null;
    private final int quality = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String resolution = null;
    private final int tbr = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String url = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Object vbr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vcodec = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String video_ext = null;
    private final int width = 0;
    
    public Format(@org.jetbrains.annotations.NotNull()
    java.lang.Object abr, @org.jetbrains.annotations.NotNull()
    java.lang.String acodec, double aspect_ratio, @org.jetbrains.annotations.NotNull()
    java.lang.String audio_ext, @org.jetbrains.annotations.NotNull()
    java.lang.String cookies, @org.jetbrains.annotations.NotNull()
    java.lang.String dynamic_range, @org.jetbrains.annotations.NotNull()
    java.lang.String ext, int filesize, @org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    java.lang.String format_id, @org.jetbrains.annotations.NotNull()
    java.lang.String format_note, int height, @org.jetbrains.annotations.NotNull()
    com.example.apiproject.data.models.HttpHeadersX http_headers, int preference, @org.jetbrains.annotations.NotNull()
    java.lang.String protocol, int quality, @org.jetbrains.annotations.NotNull()
    java.lang.String resolution, int tbr, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.Object vbr, @org.jetbrains.annotations.NotNull()
    java.lang.String vcodec, @org.jetbrains.annotations.NotNull()
    java.lang.String video_ext, int width) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.Object getAbr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAcodec() {
        return null;
    }
    
    public final double getAspect_ratio() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAudio_ext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCookies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDynamic_range() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExt() {
        return null;
    }
    
    public final int getFilesize() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormat_id() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormat_note() {
        return null;
    }
    
    public final int getHeight() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.models.HttpHeadersX getHttp_headers() {
        return null;
    }
    
    public final int getPreference() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProtocol() {
        return null;
    }
    
    public final int getQuality() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResolution() {
        return null;
    }
    
    public final int getTbr() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.Object getVbr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVcodec() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVideo_ext() {
        return null;
    }
    
    public final int getWidth() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.Object component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.models.HttpHeadersX component13() {
        return null;
    }
    
    public final int component14() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    public final int component16() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    public final int component18() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.Object component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component22() {
        return null;
    }
    
    public final int component23() {
        return 0;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.models.Format copy(@org.jetbrains.annotations.NotNull()
    java.lang.Object abr, @org.jetbrains.annotations.NotNull()
    java.lang.String acodec, double aspect_ratio, @org.jetbrains.annotations.NotNull()
    java.lang.String audio_ext, @org.jetbrains.annotations.NotNull()
    java.lang.String cookies, @org.jetbrains.annotations.NotNull()
    java.lang.String dynamic_range, @org.jetbrains.annotations.NotNull()
    java.lang.String ext, int filesize, @org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    java.lang.String format_id, @org.jetbrains.annotations.NotNull()
    java.lang.String format_note, int height, @org.jetbrains.annotations.NotNull()
    com.example.apiproject.data.models.HttpHeadersX http_headers, int preference, @org.jetbrains.annotations.NotNull()
    java.lang.String protocol, int quality, @org.jetbrains.annotations.NotNull()
    java.lang.String resolution, int tbr, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.Object vbr, @org.jetbrains.annotations.NotNull()
    java.lang.String vcodec, @org.jetbrains.annotations.NotNull()
    java.lang.String video_ext, int width) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}