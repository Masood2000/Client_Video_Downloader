package com.example.apiproject.ui.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\u0005\u00a2\u0006\u0002\u0010\u0003J$\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0012H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0012H\u0016J\u0014\u0010!\u001a\u00020\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006$"}, d2 = {"Lcom/example/apiproject/ui/adapters/DownloadingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/apiproject/ui/adapters/DownloadingAdapter$ViewHolder;", "()V", "deleteClick", "Lcom/example/apiproject/data/interfaces/DeleteClickInterface;", "getDeleteClick", "()Lcom/example/apiproject/data/interfaces/DeleteClickInterface;", "setDeleteClick", "(Lcom/example/apiproject/data/interfaces/DeleteClickInterface;)V", "downloadedData", "", "Lcom/example/apiproject/data/database/entity/DownloadingVideo;", "getDownloadedData", "()Ljava/util/List;", "setDownloadedData", "(Ljava/util/List;)V", "generateId", "", "url", "", "path", "pathAsDirectory", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "ViewHolder", "VideoDownloader (4) - 1.4_debug"})
public final class DownloadingAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.apiproject.ui.adapters.DownloadingAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.apiproject.data.database.entity.DownloadingVideo> downloadedData;
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.data.interfaces.DeleteClickInterface deleteClick;
    
    public DownloadingAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.apiproject.data.database.entity.DownloadingVideo> getDownloadedData() {
        return null;
    }
    
    public final void setDownloadedData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.apiproject.data.database.entity.DownloadingVideo> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.apiproject.data.interfaces.DeleteClickInterface getDeleteClick() {
        return null;
    }
    
    public final void setDeleteClick(@org.jetbrains.annotations.Nullable()
    com.example.apiproject.data.interfaces.DeleteClickInterface p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.apiproject.ui.adapters.DownloadingAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    private final int generateId(java.lang.String url, java.lang.String path, boolean pathAsDirectory) {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.ui.adapters.DownloadingAdapter.ViewHolder holder, int position) {
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.apiproject.data.database.entity.DownloadingVideo> data) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/apiproject/ui/adapters/DownloadingAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/apiproject/databinding/DownloadingItemBinding;", "(Lcom/example/apiproject/databinding/DownloadingItemBinding;)V", "getBinding", "()Lcom/example/apiproject/databinding/DownloadingItemBinding;", "VideoDownloader (4) - 1.4_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.apiproject.databinding.DownloadingItemBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.apiproject.databinding.DownloadingItemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.apiproject.databinding.DownloadingItemBinding getBinding() {
            return null;
        }
    }
}