package com.capstone.cookpocket.view.ui.search.detail_search;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0003J\u001a\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/capstone/cookpocket/view/ui/search/detail_search/DetailSearchActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/capstone/cookpocket/databinding/ActivityDetailSearchBinding;", "userPreferences", "Lcom/capstone/cookpocket/Network/UserPreferences;", "displayProductDetails", "", "product", "Lcom/capstone/cookpocket/Network/Response/Product;", "navigateToOrderConfirmation", "userId", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class DetailSearchActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.capstone.cookpocket.databinding.ActivityDetailSearchBinding binding;
    private com.capstone.cookpocket.Network.UserPreferences userPreferences;
    
    public DetailSearchActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void displayProductDetails(com.capstone.cookpocket.Network.Response.Product product) {
    }
    
    private final void navigateToOrderConfirmation(int userId, com.capstone.cookpocket.Network.Response.Product product) {
    }
}