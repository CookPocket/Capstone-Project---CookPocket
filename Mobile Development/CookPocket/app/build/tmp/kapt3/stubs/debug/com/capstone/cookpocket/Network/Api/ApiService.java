package com.capstone.cookpocket.Network.Api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\"\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\fJ\"\u0010\r\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\fJ\"\u0010\u000e\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\"\u0010\u0014\u001a\u00020\u00102\b\b\u0001\u0010\u0015\u001a\u00020\u00122\b\b\u0001\u0010\u0016\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0017J6\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\u001a\u001a\u00020\u00122\b\b\u0001\u0010\u0015\u001a\u00020\u00122\b\b\u0001\u0010\u0016\u001a\u00020\u00122\b\b\u0001\u0010\u001b\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u001cJ,\u0010\u001d\u001a\u00020\b2\b\b\u0001\u0010\u001e\u001a\u00020\u00122\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u001f\u00a8\u0006 "}, d2 = {"Lcom/capstone/cookpocket/Network/Api/ApiService;", "", "checkout", "Lretrofit2/Call;", "Lcom/capstone/cookpocket/Network/Response/CheckoutResponse;", "checkoutRequest", "Lcom/capstone/cookpocket/Network/Response/CheckoutRequest;", "getMakananBerat", "Lcom/capstone/cookpocket/Network/Response/CookPocketResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMakananSehat", "getMakananTradisional", "getUserData", "Lcom/capstone/cookpocket/Network/Response/LoginResponse;", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "email", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/capstone/cookpocket/Network/Response/RegisterResponse;", "name", "noTelp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchProducts", "query", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.FormUrlEncoded()
    @retrofit2.http.POST(value = "auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Field(value = "name")
    @org.jetbrains.annotations.NotNull()
    java.lang.String name, @retrofit2.http.Field(value = "email")
    @org.jetbrains.annotations.NotNull()
    java.lang.String email, @retrofit2.http.Field(value = "password")
    @org.jetbrains.annotations.NotNull()
    java.lang.String password, @retrofit2.http.Field(value = "noTelp")
    @org.jetbrains.annotations.NotNull()
    java.lang.String noTelp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.RegisterResponse> $completion);
    
    @retrofit2.http.FormUrlEncoded()
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Field(value = "email")
    @org.jetbrains.annotations.NotNull()
    java.lang.String email, @retrofit2.http.Field(value = "password")
    @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.LoginResponse> $completion);
    
    @retrofit2.http.GET(value = "products/category/makanan-berat?id_category=1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMakananBerat(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.CookPocketResponse> $completion);
    
    @retrofit2.http.GET(value = "products/category/makanan-sehat?id_category=3")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMakananSehat(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.CookPocketResponse> $completion);
    
    @retrofit2.http.GET(value = "products/category/makanan-tradisional?id_category=2")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMakananTradisional(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.CookPocketResponse> $completion);
    
    @retrofit2.http.GET(value = "products/search")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchProducts(@retrofit2.http.Query(value = "query")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.CookPocketResponse> $completion);
    
    @retrofit2.http.POST(value = "checkout")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.capstone.cookpocket.Network.Response.CheckoutResponse> checkout(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.capstone.cookpocket.Network.Response.CheckoutRequest checkoutRequest);
    
    @retrofit2.http.GET(value = "user/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserData(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.LoginResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}