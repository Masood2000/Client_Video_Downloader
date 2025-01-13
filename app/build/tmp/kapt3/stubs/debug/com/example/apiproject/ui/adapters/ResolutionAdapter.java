package com.example.apiproject.ui.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000bH\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/example/apiproject/ui/adapters/ResolutionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/apiproject/ui/adapters/ResolutionAdapter$ViewHolder;", "()V", "extractedData", "Lcom/example/apiproject/data/api/ExtractedData;", "getExtractedData", "()Lcom/example/apiproject/data/api/ExtractedData;", "setExtractedData", "(Lcom/example/apiproject/data/api/ExtractedData;)V", "selectedCell", "", "getSelectedCell", "()I", "setSelectedCell", "(I)V", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "VideoDownloader (4) - 1.4_debug"})
public final class ResolutionAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.apiproject.ui.adapters.ResolutionAdapter.ViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.data.api.ExtractedData extractedData;
    private int selectedCell = 0;
    
    public ResolutionAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.apiproject.data.api.ExtractedData getExtractedData() {
        return null;
    }
    
    public final void setExtractedData(@org.jetbrains.annotations.Nullable()
    com.example.apiproject.data.api.ExtractedData p0) {
    }
    
    public final int getSelectedCell() {
        return 0;
    }
    
    public final void setSelectedCell(int p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.apiproject.ui.adapters.ResolutionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.ui.adapters.ResolutionAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/apiproject/ui/adapters/ResolutionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/apiproject/databinding/SelectionItemBinding;", "(Lcom/example/apiproject/databinding/SelectionItemBinding;)V", "getBinding", "()Lcom/example/apiproject/databinding/SelectionItemBinding;", "VideoDownloader (4) - 1.4_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.apiproject.databinding.SelectionItemBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.apiproject.databinding.SelectionItemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.apiproject.databinding.SelectionItemBinding getBinding() {
            return null;
        }
    }
}