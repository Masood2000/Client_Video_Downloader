package com.example.apiproject.ui.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002#$B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0018H\u0016J\u0014\u0010!\u001a\u00020\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006%"}, d2 = {"Lcom/example/apiproject/ui/adapters/DownloadedAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/apiproject/ui/adapters/DownloadedAdapter$ViewHolder;", "()V", "clickHandler", "Lcom/example/apiproject/data/interfaces/ClickBundleHandler;", "getClickHandler", "()Lcom/example/apiproject/data/interfaces/ClickBundleHandler;", "setClickHandler", "(Lcom/example/apiproject/data/interfaces/ClickBundleHandler;)V", "downloadedData", "", "Lcom/example/apiproject/data/database/entity/DownloadedVideo;", "getDownloadedData", "()Ljava/util/List;", "setDownloadedData", "(Ljava/util/List;)V", "onVideoSelectedListener", "Lcom/example/apiproject/ui/adapters/DownloadedAdapter$OnVideoSelectedListener;", "getOnVideoSelectedListener", "()Lcom/example/apiproject/ui/adapters/DownloadedAdapter$OnVideoSelectedListener;", "setOnVideoSelectedListener", "(Lcom/example/apiproject/ui/adapters/DownloadedAdapter$OnVideoSelectedListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "OnVideoSelectedListener", "ViewHolder", "VideoDownloader (3) - 1.3_debug"})
public final class DownloadedAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.apiproject.ui.adapters.DownloadedAdapter.ViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.ui.adapters.DownloadedAdapter.OnVideoSelectedListener onVideoSelectedListener;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.apiproject.data.database.entity.DownloadedVideo> downloadedData;
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.data.interfaces.ClickBundleHandler clickHandler;
    
    public DownloadedAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.apiproject.ui.adapters.DownloadedAdapter.OnVideoSelectedListener getOnVideoSelectedListener() {
        return null;
    }
    
    public final void setOnVideoSelectedListener(@org.jetbrains.annotations.Nullable()
    com.example.apiproject.ui.adapters.DownloadedAdapter.OnVideoSelectedListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.apiproject.data.database.entity.DownloadedVideo> getDownloadedData() {
        return null;
    }
    
    public final void setDownloadedData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.apiproject.data.database.entity.DownloadedVideo> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.apiproject.data.interfaces.ClickBundleHandler getClickHandler() {
        return null;
    }
    
    public final void setClickHandler(@org.jetbrains.annotations.Nullable()
    com.example.apiproject.data.interfaces.ClickBundleHandler p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.apiproject.ui.adapters.DownloadedAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.ui.adapters.DownloadedAdapter.ViewHolder holder, int position) {
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.apiproject.data.database.entity.DownloadedVideo> data) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\nH&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&\u00a8\u0006\u0013"}, d2 = {"Lcom/example/apiproject/ui/adapters/DownloadedAdapter$OnVideoSelectedListener;", "", "dummyItemClicked", "", "url", "", "onItemClicked", "position", "", "media", "Lcom/example/apiproject/data/database/entity/DownloadedVideo;", "onOptionsClicked", "view", "Landroid/view/View;", "download", "toggleSelectionMode", "isSelection", "", "allSelected", "VideoDownloader (3) - 1.3_debug"})
    public static abstract interface OnVideoSelectedListener {
        
        public abstract void onOptionsClicked(@org.jetbrains.annotations.NotNull()
        android.view.View view, int position, @org.jetbrains.annotations.NotNull()
        com.example.apiproject.data.database.entity.DownloadedVideo download);
        
        public abstract void toggleSelectionMode(boolean isSelection, boolean allSelected);
        
        public abstract void onItemClicked(int position, @org.jetbrains.annotations.NotNull()
        com.example.apiproject.data.database.entity.DownloadedVideo media);
        
        public abstract void dummyItemClicked(@org.jetbrains.annotations.NotNull()
        java.lang.String url);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/apiproject/ui/adapters/DownloadedAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/apiproject/databinding/DownloadedItemBinding;", "(Lcom/example/apiproject/databinding/DownloadedItemBinding;)V", "getBinding", "()Lcom/example/apiproject/databinding/DownloadedItemBinding;", "VideoDownloader (3) - 1.3_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.apiproject.databinding.DownloadedItemBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.apiproject.databinding.DownloadedItemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.apiproject.databinding.DownloadedItemBinding getBinding() {
            return null;
        }
    }
}