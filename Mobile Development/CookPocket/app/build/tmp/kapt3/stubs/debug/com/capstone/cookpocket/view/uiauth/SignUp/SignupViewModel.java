package com.capstone.cookpocket.view.uiauth.SignUp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/capstone/cookpocket/view/uiauth/SignUp/SignupViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/capstone/cookpocket/view/uiauth/AuthRepository;", "(Lcom/capstone/cookpocket/view/uiauth/AuthRepository;)V", "_registerState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/capstone/cookpocket/Network/Response/RegisterResponse;", "registerState", "Lkotlinx/coroutines/flow/StateFlow;", "getRegisterState", "()Lkotlinx/coroutines/flow/StateFlow;", "register", "", "name", "", "email", "password", "app_debug"})
public final class SignupViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.capstone.cookpocket.view.uiauth.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.capstone.cookpocket.Network.Response.RegisterResponse> _registerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.capstone.cookpocket.Network.Response.RegisterResponse> registerState = null;
    
    public SignupViewModel(@org.jetbrains.annotations.NotNull()
    com.capstone.cookpocket.view.uiauth.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.capstone.cookpocket.Network.Response.RegisterResponse> getRegisterState() {
        return null;
    }
    
    public final void register(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
}