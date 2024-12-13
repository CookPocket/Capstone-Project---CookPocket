package com.capstone.cookpocket.view.ui.search.order;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J0\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J \u0010\u000f\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0012\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/capstone/cookpocket/view/ui/search/order/OrderAntarActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/capstone/cookpocket/databinding/ActivityOrderAntarBinding;", "deliveryCost", "", "quantity", "checkoutOrder", "", "userId", "productId", "productName", "", "productPrice", "displayOrderDetails", "productImage", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "updateOrderDetails", "app_debug"})
public final class OrderAntarActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.capstone.cookpocket.databinding.ActivityOrderAntarBinding binding;
    private int quantity = 1;
    private int deliveryCost = 10000;
    
    public OrderAntarActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void displayOrderDetails(java.lang.String productName, java.lang.String productImage, int productPrice) {
    }
    
    private final void updateOrderDetails(int productPrice) {
    }
    
    private final void checkoutOrder(int userId, int productId, java.lang.String productName, int productPrice, int quantity) {
    }
}