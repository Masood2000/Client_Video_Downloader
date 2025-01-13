package com.example.apiproject.ui.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u000bH\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u000bH\u0017J\u0018\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u0014\u0010\u001c\u001a\u00020\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/apiproject/ui/adapters/TabsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/apiproject/ui/adapters/TabsAdapter$ViewHolder;", "()V", "clickHandler", "Lcom/example/apiproject/data/interfaces/ClickHandler;", "getClickHandler", "()Lcom/example/apiproject/data/interfaces/ClickHandler;", "setClickHandler", "(Lcom/example/apiproject/data/interfaces/ClickHandler;)V", "selectedTab", "", "getSelectedTab", "()I", "setSelectedTab", "(I)V", "tabsData", "", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "ViewHolder", "VideoDownloader (5) - 1.5_debug"})
public final class TabsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.apiproject.ui.adapters.TabsAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<java.lang.String> tabsData;
    @org.jetbrains.annotations.Nullable()
    private com.example.apiproject.data.interfaces.ClickHandler clickHandler;
    private int selectedTab = 0;
    
    public TabsAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.apiproject.data.interfaces.ClickHandler getClickHandler() {
        return null;
    }
    
    public final void setClickHandler(@org.jetbrains.annotations.Nullable()
    com.example.apiproject.data.interfaces.ClickHandler p0) {
    }
    
    public final int getSelectedTab() {
        return 0;
    }
    
    public final void setSelectedTab(int p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.apiproject.ui.adapters.TabsAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"ResourceAsColor"})
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.apiproject.ui.adapters.TabsAdapter.ViewHolder holder, int position) {
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> data) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/apiproject/ui/adapters/TabsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/apiproject/databinding/TablayoutBinding;", "(Lcom/example/apiproject/databinding/TablayoutBinding;)V", "getBinding", "()Lcom/example/apiproject/databinding/TablayoutBinding;", "VideoDownloader (5) - 1.5_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.apiproject.databinding.TablayoutBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.apiproject.databinding.TablayoutBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.apiproject.databinding.TablayoutBinding getBinding() {
            return null;
        }
    }
}