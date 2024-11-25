package com.example.apiproject.ui.activity;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u0000 a2\u00020\u0001:\u0001aB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\b\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010<\u001a\u00020=2\u0006\u0010:\u001a\u00020;H\u0002J\u0018\u0010>\u001a\u0002072\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0006H\u0002J\u0010\u0010B\u001a\u00020\u00062\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u00020DH\u0002J\u000e\u0010F\u001a\u0002072\u0006\u0010G\u001a\u00020\u0006J\u0016\u0010H\u001a\u0002072\u0006\u0010G\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u0006J\b\u0010J\u001a\u00020KH\u0002J\b\u0010L\u001a\u00020DH\u0002J\u0006\u0010M\u001a\u000207J\b\u0010N\u001a\u000207H\u0002J\u0018\u0010O\u001a\u0004\u0018\u00010\u00062\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020KJ\u0006\u0010S\u001a\u000207J\u0012\u0010T\u001a\u0002072\b\u0010U\u001a\u0004\u0018\u00010VH\u0014J\b\u0010W\u001a\u000207H\u0014J\b\u0010X\u001a\u000207H\u0014J\b\u0010Y\u001a\u000207H\u0002J\u0010\u0010Z\u001a\u0002072\u0006\u0010P\u001a\u00020QH\u0002J\b\u0010[\u001a\u000207H\u0002J\b\u0010\\\u001a\u000207H\u0002J\u0014\u0010]\u001a\u0002072\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00060_J\b\u0010`\u001a\u000207H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020&8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010\f\u001a\u0004\b\'\u0010(R \u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001b\u00101\u001a\u0002028FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u0010\f\u001a\u0004\b3\u00104\u00a8\u0006b"}, d2 = {"Lcom/example/apiproject/ui/activity/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "activityResultLauncherForVideo", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "binding", "Lcom/example/apiproject/databinding/ActivityMainBinding;", "getBinding", "()Lcom/example/apiproject/databinding/ActivityMainBinding;", "binding$delegate", "Lkotlin/Lazy;", "clipboardManager", "Landroid/content/ClipboardManager;", "getClipboardManager", "()Landroid/content/ClipboardManager;", "setClipboardManager", "(Landroid/content/ClipboardManager;)V", "downloadLink", "getDownloadLink", "()Ljava/lang/String;", "setDownloadLink", "(Ljava/lang/String;)V", "lastAutoLink", "getLastAutoLink", "setLastAutoLink", "navController", "Landroidx/navigation/NavController;", "onSplashLinkDetected", "", "getOnSplashLinkDetected", "()Z", "setOnSplashLinkDetected", "(Z)V", "permissionStateListener", "Lcom/example/apiproject/data/interfaces/ClickHandler;", "preferences", "Lcom/example/apiproject/data/Preferences/SharedPreference;", "getPreferences", "()Lcom/example/apiproject/data/Preferences/SharedPreference;", "preferences$delegate", "reelsData", "", "Lcom/example/apiproject/data/models/ReelVideo;", "getReelsData", "()Ljava/util/List;", "setReelsData", "(Ljava/util/List;)V", "viewModel", "Lcom/example/apiproject/domain/viewmodels/MainActivityViewModel;", "getViewModel", "()Lcom/example/apiproject/domain/viewmodels/MainActivityViewModel;", "viewModel$delegate", "checkAskAgainForVideo", "", "createAndShowBottomSheet", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "view", "Landroid/view/View;", "createAndShowDialog", "Landroid/app/Dialog;", "downloadOptionSheet", "extractedData", "Lcom/example/apiproject/data/api/ExtractedData;", "url", "formatSize", "size", "", "getConsumedPhoneStorage", "getDownloadMetaData", "link", "getDownloadReel", "videoId", "getProgressPercentage", "", "getTotalPhoneStorage", "handleResume", "initView", "loadJSONFromRaw", "context", "Landroid/content/Context;", "resourceId", "moveToFirstItem", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "openPermissionSettings", "parseJSON", "requestVideoAccessPermission", "retrieveCopiedLink", "shareFiles", "filePaths", "", "showRationalDialog", "Companion", "VideoDownloader (3) - 1.3_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.apiproject.data.models.ReelVideo> reelsData;
    private boolean onSplashLinkDetected = false;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.data.interfaces.ClickHandler permissionStateListener;
    private androidx.navigation.NavController navController;
    @org.jetbrains.annotations.Nullable()
    private android.content.ClipboardManager clipboardManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy preferences$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String downloadLink = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastAutoLink = "none";
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> activityResultLauncherForVideo = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "Main_Activity";
    @org.jetbrains.annotations.Nullable()
    private static com.example.apiproject.data.interfaces.UpdateData updateData;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.ui.activity.MainActivity.Companion Companion = null;
    
    @javax.inject.Inject()
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.apiproject.data.models.ReelVideo> getReelsData() {
        return null;
    }
    
    public final void setReelsData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.apiproject.data.models.ReelVideo> p0) {
    }
    
    public final boolean getOnSplashLinkDetected() {
        return false;
    }
    
    public final void setOnSplashLinkDetected(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.domain.viewmodels.MainActivityViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.ClipboardManager getClipboardManager() {
        return null;
    }
    
    public final void setClipboardManager(@org.jetbrains.annotations.Nullable()
    android.content.ClipboardManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.data.Preferences.SharedPreference getPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.databinding.ActivityMainBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDownloadLink() {
        return null;
    }
    
    public final void setDownloadLink(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastAutoLink() {
        return null;
    }
    
    public final void setLastAutoLink(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String loadJSONFromRaw(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int resourceId) {
        return null;
    }
    
    private final void parseJSON(android.content.Context context) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void retrieveCopiedLink() {
    }
    
    public final void getDownloadMetaData(@org.jetbrains.annotations.NotNull()
    java.lang.String link) {
    }
    
    public final void getDownloadReel(@org.jetbrains.annotations.NotNull()
    java.lang.String link, @org.jetbrains.annotations.NotNull()
    java.lang.String videoId) {
    }
    
    private final com.google.android.material.bottomsheet.BottomSheetDialog createAndShowBottomSheet(android.view.View view) {
        return null;
    }
    
    private final android.app.Dialog createAndShowDialog(android.view.View view) {
        return null;
    }
    
    private final long getConsumedPhoneStorage() {
        return 0L;
    }
    
    private final java.lang.String formatSize(long size) {
        return null;
    }
    
    private final long getTotalPhoneStorage() {
        return 0L;
    }
    
    private final int getProgressPercentage() {
        return 0;
    }
    
    private final void downloadOptionSheet(com.example.apiproject.data.api.ExtractedData extractedData, java.lang.String url) {
    }
    
    private final void initView() {
    }
    
    public final void moveToFirstItem() {
    }
    
    private final void openPermissionSettings() {
    }
    
    private final void showRationalDialog() {
    }
    
    private final void checkAskAgainForVideo() {
    }
    
    private final void requestVideoAccessPermission() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    public final void handleResume() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    public final void shareFiles(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> filePaths) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/apiproject/ui/activity/MainActivity$Companion;", "", "()V", "TAG", "", "updateData", "Lcom/example/apiproject/data/interfaces/UpdateData;", "getUpdateData", "()Lcom/example/apiproject/data/interfaces/UpdateData;", "setUpdateData", "(Lcom/example/apiproject/data/interfaces/UpdateData;)V", "VideoDownloader (3) - 1.3_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.apiproject.data.interfaces.UpdateData getUpdateData() {
            return null;
        }
        
        public final void setUpdateData(@org.jetbrains.annotations.Nullable()
        com.example.apiproject.data.interfaces.UpdateData p0) {
        }
    }
}