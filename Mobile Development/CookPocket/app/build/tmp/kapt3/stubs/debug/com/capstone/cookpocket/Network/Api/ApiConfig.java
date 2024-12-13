package com.capstone.cookpocket.Network.Api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/capstone/cookpocket/Network/Api/ApiConfig;", "", "()V", "BASE_URL", "", "CHECKOUT_URL", "getApiService", "Lcom/capstone/cookpocket/Network/Api/ApiService;", "token", "getCheckoutApiService", "app_debug"})
public final class ApiConfig {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://cookpocket.et.r.appspot.com/api/";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHECKOUT_URL = "https://cookpocket.et.r.appspot.com/order/";
    @org.jetbrains.annotations.NotNull()
    public static final com.capstone.cookpocket.Network.Api.ApiConfig INSTANCE = null;
    
    private ApiConfig() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.capstone.cookpocket.Network.Api.ApiService getApiService(@org.jetbrains.annotations.Nullable()
    java.lang.String token) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.capstone.cookpocket.Network.Api.ApiService getCheckoutApiService(@org.jetbrains.annotations.Nullable()
    java.lang.String token) {
        return null;
    }
}