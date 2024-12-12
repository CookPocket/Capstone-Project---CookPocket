package com.capstone.cookpocket.Network.Api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\"\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u000b2\b\b\u0001\u0010\u0010\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0011J6\u0010\u0012\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\u000b2\b\b\u0001\u0010\u000f\u001a\u00020\u000b2\b\b\u0001\u0010\u0010\u001a\u00020\u000b2\b\b\u0001\u0010\u0015\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\"\u0010\u0017\u001a\u00020\u00182\b\b\u0001\u0010\u0019\u001a\u00020\u001a2\b\b\u0001\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/capstone/cookpocket/Network/Api/ApiService;", "", "getAllStories", "Lcom/capstone/cookpocket/Network/Response/StoryResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStoryDetail", "Lcom/capstone/cookpocket/Network/Response/StoryDetailResponse;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/capstone/cookpocket/Network/Response/LoginResponse;", "email", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/capstone/cookpocket/Network/Response/RegisterResponse;", "name", "noTelp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadStory", "Lcom/capstone/cookpocket/Network/Response/FileUploadResponse;", "photo", "Lokhttp3/MultipartBody$Part;", "description", "Lokhttp3/RequestBody;", "(Lokhttp3/MultipartBody$Part;Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
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
    
    @retrofit2.http.GET(value = "stories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllStories(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.StoryResponse> $completion);
    
    @retrofit2.http.GET(value = "stories/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStoryDetail(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.StoryDetailResponse> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "stories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadStory(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part photo, @retrofit2.http.Part(value = "description")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody description, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.capstone.cookpocket.Network.Response.FileUploadResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}