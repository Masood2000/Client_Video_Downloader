package com.example.apiproject.ui.activity;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0006H\u0002J\u0010\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\u000e\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020\u0006J\u0016\u0010(\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006J\b\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020$H\u0002J\b\u0010-\u001a\u00020\u0017H\u0002J\b\u0010.\u001a\u00020\u0017H\u0002J\u0012\u0010/\u001a\u00020\u00172\b\u00100\u001a\u0004\u0018\u000101H\u0014J\b\u00102\u001a\u00020\u0017H\u0014J\b\u00103\u001a\u00020\u0017H\u0014J\b\u00104\u001a\u00020\u0017H\u0014J\b\u00105\u001a\u00020\u0017H\u0002J\b\u00106\u001a\u00020\u0017H\u0002J\b\u00107\u001a\u00020\u0017H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\f\u001a\u0004\b\u0013\u0010\u0014\u00a8\u00069"}, d2 = {"Lcom/example/apiproject/ui/activity/BrowserActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "activityResultLauncherForVideo", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "binding", "Lcom/example/apiproject/databinding/ActivityBrowserCastingBinding;", "getBinding", "()Lcom/example/apiproject/databinding/ActivityBrowserCastingBinding;", "binding$delegate", "Lkotlin/Lazy;", "navController", "Landroidx/navigation/NavController;", "permissionStateListener", "Lcom/example/apiproject/data/interfaces/ClickHandler;", "viewModel", "Lcom/example/apiproject/domain/viewmodels/MainActivityViewModel;", "getViewModel", "()Lcom/example/apiproject/domain/viewmodels/MainActivityViewModel;", "viewModel$delegate", "checkAskAgainForVideo", "", "createAndShowBottomSheet", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "view", "Landroid/view/View;", "createAndShowDialog", "Landroid/app/Dialog;", "downloadOptionSheet", "extractedData", "Lcom/example/apiproject/data/api/ExtractedData;", "url", "formatSize", "size", "", "getConsumedPhoneStorage", "getDownloadMetaData", "link", "getDownloadReel", "videoId", "getProgressPercentage", "", "getTotalPhoneStorage", "initListeners", "initViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "openPermissionSettings", "requestVideoAccessPermission", "showRationalDialog", "Companion", "VideoDownloader (3) - 1.3_debug"})
public final class BrowserActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.data.interfaces.ClickHandler permissionStateListener;
    private androidx.navigation.NavController navController;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> activityResultLauncherForVideo = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "BROWSER_CASTING_ACTIVITY";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apiproject.ui.activity.BrowserActivity.Companion Companion = null;
    
    public BrowserActivity() {
        super();
    }
    
    private final com.example.apiproject.databinding.ActivityBrowserCastingBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.apiproject.domain.viewmodels.MainActivityViewModel getViewModel() {
        return null;
    }
    
    /**
     * *
     * Life Cycle Related Functions
     */
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    /**
     * *
     * View Handling Functions
     */
    private final void initViews() {
    }
    
    private final void initListeners() {
    }
    
    /**
     * *
     * Other Functionality
     */
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
    
    private final java.lang.String formatSize(long size) {
        return null;
    }
    
    private final long getTotalPhoneStorage() {
        return 0L;
    }
    
    private final int getProgressPercentage() {
        return 0;
    }
    
    private final long getConsumedPhoneStorage() {
        return 0L;
    }
    
    private final void downloadOptionSheet(com.example.apiproject.data.api.ExtractedData extractedData, java.lang.String url) {
    }
    
    private final void openPermissionSettings() {
    }
    
    private final void showRationalDialog() {
    }
    
    private final void checkAskAgainForVideo() {
    }
    
    private final void requestVideoAccessPermission() {
    }
    
    /**
     * *
     * / Other Functionality
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/apiproject/ui/activity/BrowserActivity$Companion;", "", "()V", "TAG", "", "VideoDownloader (3) - 1.3_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}