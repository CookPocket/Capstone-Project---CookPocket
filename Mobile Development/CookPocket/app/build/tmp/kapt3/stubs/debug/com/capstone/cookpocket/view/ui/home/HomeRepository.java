package com.capstone.cookpocket.view.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\"\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\tJ,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0018"}, d2 = {"Lcom/capstone/cookpocket/view/ui/home/HomeRepository;", "", "api", "Lcom/capstone/cookpocket/Network/Api/ApiService;", "userPreferences", "Lcom/capstone/cookpocket/Network/UserPreferences;", "(Lcom/capstone/cookpocket/Network/Api/ApiService;Lcom/capstone/cookpocket/Network/UserPreferences;)V", "clearToken", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStories", "Lkotlin/Result;", "", "Lcom/capstone/cookpocket/Network/Response/ListStoryItem;", "getStories-IoAF18A", "uploadStory", "Lcom/capstone/cookpocket/Network/Response/FileUploadResponse;", "photo", "Lokhttp3/MultipartBody$Part;", "description", "Lokhttp3/RequestBody;", "uploadStory-0E7RQCE", "(Lokhttp3/MultipartBody$Part;Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class HomeRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.capstone.cookpocket.Network.Api.ApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.capstone.cookpocket.Network.UserPreferences userPreferences = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.capstone.cookpocket.view.ui.home.HomeRepository INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.capstone.cookpocket.view.ui.home.HomeRepository.Companion Companion = null;
    
    public HomeRepository(@org.jetbrains.annotations.NotNull()
    com.capstone.cookpocket.Network.Api.ApiService api, @org.jetbrains.annotations.NotNull()
    com.capstone.cookpocket.Network.UserPreferences userPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearToken(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/capstone/cookpocket/view/ui/home/HomeRepository$Companion;", "", "()V", "INSTANCE", "Lcom/capstone/cookpocket/view/ui/home/HomeRepository;", "getInstance", "api", "Lcom/capstone/cookpocket/Network/Api/ApiService;", "userPreferences", "Lcom/capstone/cookpocket/Network/UserPreferences;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.capstone.cookpocket.view.ui.home.HomeRepository getInstance(@org.jetbrains.annotations.NotNull()
        com.capstone.cookpocket.Network.Api.ApiService api, @org.jetbrains.annotations.NotNull()
        com.capstone.cookpocket.Network.UserPreferences userPreferences) {
            return null;
        }
    }
}