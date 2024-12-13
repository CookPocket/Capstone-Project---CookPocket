package com.capstone.cookpocket.view.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\"\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0013J\"\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0013J\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cJ\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001a0\u0019J\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001fR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006!"}, d2 = {"Lcom/capstone/cookpocket/view/ui/home/HomeRepository;", "", "api", "Lcom/capstone/cookpocket/Network/Api/ApiService;", "userPreferences", "Lcom/capstone/cookpocket/Network/UserPreferences;", "(Lcom/capstone/cookpocket/Network/Api/ApiService;Lcom/capstone/cookpocket/Network/UserPreferences;)V", "_allStories", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/capstone/cookpocket/Network/Response/Product;", "_filteredStories", "filteredStories", "Landroidx/lifecycle/LiveData;", "getFilteredStories", "()Landroidx/lifecycle/LiveData;", "RepMakananBerat", "Lkotlin/Result;", "RepMakananBerat-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "RepMakananSehat", "RepMakananSehat-IoAF18A", "RepMakananTradisional", "RepMakananTradisional-IoAF18A", "getPagedSearchResults", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "query", "", "getPagedStories", "searchProducts", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class HomeRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.capstone.cookpocket.Network.Api.ApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.capstone.cookpocket.Network.UserPreferences userPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.capstone.cookpocket.Network.Response.Product>> _allStories = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.capstone.cookpocket.Network.Response.Product>> _filteredStories = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.capstone.cookpocket.Network.Response.Product>> filteredStories = null;
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
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.capstone.cookpocket.Network.Response.Product>> getFilteredStories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.capstone.cookpocket.Network.Response.Product>> getPagedSearchResults(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.capstone.cookpocket.Network.Response.Product>> getPagedStories() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchProducts(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.capstone.cookpocket.Network.Response.Product>> $completion) {
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