package com.capstone.cookpocket.view.ui.search.order

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstone.cookpocket.Network.Api.ApiConfig
import com.capstone.cookpocket.Network.Response.CartItem
import com.capstone.cookpocket.Network.Response.CheckoutRequest
import com.capstone.cookpocket.Network.Response.CheckoutResponse
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.databinding.ActivityOrderAntarBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderAntarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderAntarBinding
    private var quantity: Int = 1
    private var deliveryCost: Int = 10000 // Biaya pengiriman dummy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderAntarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data preferensi pengguna
        val userPreferences = UserPreferences(this)

        lifecycleScope.launch {
            userPreferences.userName.collect { username ->
                binding.tvUsernameUser.text = username
            }
            userPreferences.noTelp.collect { noTelp ->
                binding.tvNoTelpUser.text = noTelp ?: "Nomor telepon tidak tersedia"
            }
        }

        // Mengambil data dari Intent
        val userId = intent.getIntExtra("USER_ID", -1)
        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        val productName = intent.getStringExtra("PRODUCT_NAME") ?: "Produk Tidak Dikenal"
        val productImage = intent.getStringExtra("PRODUCT_IMAGE") ?: ""
        val productPrice = intent.getIntExtra("PRODUCT_PRICE", 0)

        // Menampilkan detail pesanan saat aktivitas dibuat
        displayOrderDetails(productName, productImage, productPrice)

        // Menghitung dan menampilkan harga awal
        updateOrderDetails(productPrice)

        binding.btnPlus.setOnClickListener {
            quantity++
            updateOrderDetails(productPrice)
        }

        binding.btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateOrderDetails(productPrice)
            } else {
                finish()
            }
        }

        binding.btnBuatPesanan.setOnClickListener {
            if (userId != -1 && productId != -1 && productPrice > 0) {
                checkoutOrder(userId, productId, productName, productPrice, quantity)

                // Mengirim data produk ke OrderFinishActivity
                val intent = Intent(this, OrderFinishActivity::class.java).apply {
                    putExtra("PRODUCT_NAME", productName)
                    putExtra("PRODUCT_IMAGE", productImage)
                    putExtra("PRODUCT_PRICE", productPrice)
                    putExtra("TOTAL_PRICE", productPrice * quantity)
                    putExtra("DELIVERY_COST", deliveryCost)  // Mengirimkan biaya pengiriman
                    putExtra("USER_ADDRESS", "JL. Urip Sumoeharjo No.91")
                    putExtra("STORE_NAME", "Frozen Food Store")
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Data pesanan tidak valid", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBackAntar.setOnClickListener {
            finish()
        }
        binding.cvBtnAmbilSendiri.setOnClickListener{
            val intent = Intent(this, OrderAmbilSendiriActivity::class.java).apply {
                putExtra("PRODUCT_NAME", productName)
                putExtra("PRODUCT_IMAGE", productImage)
                putExtra("PRODUCT_PRICE", productPrice)
            }
            startActivity(intent)
        }
    }

    // Menampilkan detail pesanan
    private fun displayOrderDetails(productName: String, productImage: String, productPrice: Int) {
        binding.apply {
            tvNamaMakanan.text = productName
            tvHargaMakanan.text = "Rp. $productPrice"
            Glide.with(this@OrderAntarActivity)
                .load(productImage)
                .centerCrop()
                .into(imgFood)
        }
    }

    // Memperbarui detail pesanan saat jumlah produk berubah
    private fun updateOrderDetails(productPrice: Int) {
        binding.tvQuantity.text = quantity.toString()
        val totalPrice = quantity * productPrice
        binding.totalPesanan.text = "Rp. $totalPrice"
        binding.totalBiayaPengiriman.text = "Rp. 10000"

        // Menambahkan biaya pengiriman ke total harga
        val totalWithDelivery = totalPrice + deliveryCost
        binding.totalSemua.text = "Rp. $totalWithDelivery"  // Menampilkan total harga termasuk biaya pengiriman
    }

    // Menangani proses checkout
    private fun checkoutOrder(userId: Int, productId: Int, productName: String, productPrice: Int, quantity: Int) {
        val cartItems = listOf(
            CartItem(
                productId = productId,
                quantity = quantity,
                productName = productName,
                price = productPrice,
                subtotal = productPrice * quantity
            )
        )

        val checkoutRequest = CheckoutRequest(userId = userId, cartItems = cartItems)

        ApiConfig.getCheckoutApiService().checkout(checkoutRequest).enqueue(object : Callback<CheckoutResponse> {
            override fun onResponse(call: Call<CheckoutResponse>, response: Response<CheckoutResponse>) {
                if (response.isSuccessful) {
                    val checkoutResponse = response.body()
                    if (checkoutResponse?.success == true) {
                        finish()
                    } else {

                    }
                } else {

                }
            }

            override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {

            }
        })
    }
}
