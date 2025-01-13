package com.example.apiproject.ui.fragments;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0016J*\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010!\u001a\u00020\"J\"\u0010%\u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010\'\u001a\u00020\u0013H\u0007J\b\u0010(\u001a\u00020\u0011H\u0016J\u0012\u0010)\u001a\u00020\u00112\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020-H\u0016J\u0018\u0010.\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/example/apiproject/ui/fragments/VideoCompletedFragment;", "Lcom/example/apiproject/ui/base/BaseFragment;", "()V", "adapter", "Lcom/example/apiproject/ui/adapters/DownloadedAdapter;", "getAdapter", "()Lcom/example/apiproject/ui/adapters/DownloadedAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/example/apiproject/databinding/FragmentVideoCompletedBinding;", "getBinding", "()Lcom/example/apiproject/databinding/FragmentVideoCompletedBinding;", "binding$delegate", "optionsBottomSheetDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "copyToClipboard", "", "text", "", "deleteDownload", "download", "Lcom/example/apiproject/data/database/entity/DownloadedVideo;", "position", "", "initView", "insertFileToMediaStore", "Landroid/net/Uri;", "contentResolver", "Landroid/content/ContentResolver;", "collection", "values", "Landroid/content/ContentValues;", "file", "Ljava/io/File;", "insertToMediaStore", "Lcom/example/apiproject/ui/fragments/ShareableContent;", "insertVideoToMediaStore", "relativePath", "mimeType", "lazyLoad", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setViewBinding", "Landroid/view/View;", "showOptions", "Companion", "VideoDownloader (4) - 1.4_debug"})
public final class VideoCompletedFragment extends com.example.apiproject.ui.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy adapter$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.material.bottomsheet.BottomSheetDialog optionsBottomSheetDialog;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "VideoCompletedFragment";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.ui.fragments.VideoCompletedFragment.Companion Companion = null;
    
    public VideoCompletedFragment() {
        super();
    }
    
    private final com.example.apiproject.databinding.FragmentVideoCompletedBinding getBinding() {
        return null;
    }
    
    private final com.example.apiproject.ui.adapters.DownloadedAdapter getAdapter() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View setViewBinding() {
        return null;
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    @java.lang.Override()
    public void lazyLoad() {
    }
    
    private final void showOptions(com.example.apiproject.data.database.entity.DownloadedVideo download, int position) {
    }
    
    private final void copyToClipboard(java.lang.String text) {
    }
    
    private final void deleteDownload(com.example.apiproject.data.database.entity.DownloadedVideo download, int position) {
    }
    
    /**
     * *
     * <---------------- Share Video Functions  ------------------->
     *
     * Extra function for sharing the video to other apps ....
     */
    @org.jetbrains.annotations.Nullable()
    public final com.example.apiproject.ui.fragments.ShareableContent insertToMediaStore(@org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"InlinedApi"})
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri insertVideoToMediaStore(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    java.lang.String relativePath, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType) {
        return null;
    }
    
    private final android.net.Uri insertFileToMediaStore(android.content.ContentResolver contentResolver, android.net.Uri collection, android.content.ContentValues values, java.io.File file) {
        return null;
    }
    
    /**
     * *
     * <--------------- /Share Video Functions  ------------------->
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/apiproject/ui/fragments/VideoCompletedFragment$Companion;", "", "()V", "TAG", "", "VideoDownloader (4) - 1.4_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}