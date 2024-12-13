package com.capstone.cookpocket.Network.Response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// Response untuk Checkout
@Parcelize
data class CheckoutResponse(

    @field:SerializedName("success")
    val success: Boolean? = null,  // Status keberhasilan (true/false)

    @field:SerializedName("message")
    val message: String? = null,  // Pesan status dari server, bisa berisi info error atau sukses

    @field:SerializedName("order_id")
    val orderId: Int? = null,  // ID pesanan (seharusnya Integer, jika sesuai dengan API)

    @field:SerializedName("total_amount")
    val totalAmount: Int? = null,  // Total harga yang harus dibayar

    @field:SerializedName("items")
    val items: List<CartItem>? = null,  // Daftar barang yang dipesan

    @field:SerializedName("shipping_address")
    val shippingAddress: String? = null  // Alamat pengiriman (jika diperlukan)
) : Parcelable

// Model untuk Cart Item (Barang dalam Keranjang)
@Parcelize
data class CartItem(

    @field:SerializedName("product_id")
    val productId: Int? = null,  // ID produk dalam keranjang

    @field:SerializedName("quantity")
    val quantity: Int? = null,  // Jumlah barang dalam keranjang

    @field:SerializedName("product_name")
    val productName: String? = null,  // Nama produk

    @field:SerializedName("price")
    val price: Int? = null,  // Harga per produk

    @field:SerializedName("subtotal")
    val subtotal: Int? = null  // Harga total untuk barang ini (harga * quantity)
) : Parcelable

// Model untuk Request Checkout
data class CheckoutRequest(
    @field:SerializedName("user_id")
    val userId: Int? = null,  // ID pengguna yang melakukan checkout

    @field:SerializedName("cart_items")
    val cartItems: List<CartItem> = emptyList()  // Daftar barang yang ada dalam keranjang
)
