package com.capstone.cookpocket.view.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0012\u00a8\u0006\u001f"}, d2 = {"Lcom/capstone/cookpocket/view/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "storyRepository", "Lcom/capstone/cookpocket/view/ui/home/HomeRepository;", "(Lcom/capstone/cookpocket/view/ui/home/HomeRepository;)V", "_errorMessage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isLoading", "", "_stories", "", "Lcom/capstone/cookpocket/Network/Response/ListStoryItem;", "_uploadResult", "Lcom/capstone/cookpocket/Network/Response/FileUploadResponse;", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "isLoading", "stories", "getStories", "uploadResult", "getUploadResult", "fetchStories", "", "uploadStory", "photo", "Lokhttp3/MultipartBody$Part;", "description", "Lokhttp3/RequestBody;", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.capstone.cookpocket.view.ui.home.HomeRepository storyRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.capstone.cookpocket.Network.Response.ListStoryItem>> _stories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.capstone.cookpocket.Network.Response.FileUploadResponse> _uploadResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.capstone.cookpocket.view.ui.home.HomeRepository storyRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.capstone.cookpocket.Network.Response.ListStoryItem>> getStories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.capstone.cookpocket.Network.Response.FileUploadResponse> getUploadResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void fetchStories() {
    }
    
    public final void uploadStory(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part photo, @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody description) {
    }
}