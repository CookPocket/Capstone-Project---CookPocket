package com.capstone.cookpocket.view.ui.search.detail_search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstone.cookpocket.Network.Response.Product
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityDetailSearchBinding
import com.capstone.cookpocket.view.ui.search.order.OrderAntarActivity
import com.capstone.cookpocket.view.ui.search.order.OrderAmbilSendiriActivity
import com.capstone.cookpocket.view.uiauth.Login.LoginActivity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSearchBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences.getInstance(this)

        // Menerima data Product dari Intent
        val product = intent.getParcelableExtra<Product>("PRODUCT")

        product?.let {
            displayProductDetails(it)
        }

        binding.ivBackDetailSearch.setOnClickListener {
            finish()
        }

        // Menangani tombol "Pesan"
        binding.buttonPesan.setOnClickListener {
            // Cek apakah pengguna sudah login
            lifecycleScope.launch {
                val userId = userPreferences.idUser.firstOrNull()
                if (userId != null) {
                    // Lanjutkan ke halaman konfirmasi order
                    navigateToOrderConfirmation(userId, product)
                } else {
                    // Pengguna belum login
                    Toast.makeText(
                        this@DetailSearchActivity,
                        "Silakan login terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Navigasi ke halaman login jika belum login
                    val intent = Intent(this@DetailSearchActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayProductDetails(product: Product) {
        binding.apply {
            // Tampilkan data ke dalam UI
            tvJudul.text = product.name
            tvDeskripsi.text = product.description
            tvDeskripsiBahan.text = product.ingredient
            tvDeskripsiPembuatan.text = product.steps
            tvHargaDetailSearch.text = "Rp.${product.price}"

            // Muat gambar menggunakan Glide
            Glide.with(this@DetailSearchActivity)
                .load(product.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.rounded_image_background) // Placeholder
                .into(ivMainImage)

            // Set title di ActionBar
            supportActionBar?.title = product.name
        }
    }

    private fun navigateToOrderConfirmation(userId: Int, product: Product?) {
        // Navigate to OrderAntarActivity
        val antarIntent = Intent(this, OrderAmbilSendiriActivity::class.java).apply {
            putExtra("USER_ID", userId)
            putExtra("PRODUCT_ID", product?.idProduct)
            putExtra("PRODUCT_NAME", product?.name)
            putExtra("PRODUCT_IMAGE", product?.imageUrl)
            putExtra("PRODUCT_PRICE", product?.price)
        }
        startActivity(antarIntent)

        // Navigate to OrderAmbilSendiriActivity
        val ambilSendiriIntent = Intent(this, OrderAntarActivity::class.java).apply {
            putExtra("USER_ID", userId)
            putExtra("PRODUCT_ID", product?.idProduct)
            putExtra("PRODUCT_NAME", product?.name)
            putExtra("PRODUCT_IMAGE", product?.imageUrl)
            putExtra("PRODUCT_PRICE", product?.price)
            putExtra("DELIVERY_COST", 10000)  // Dummy delivery cost
            putExtra("USER_ADDRESS", "JL. Urip Sumoeharjo No.91")  // Example address
            putExtra("STORE_NAME", "Frozen Food Store")  // Example store name
        }
        startActivity(ambilSendiriIntent)
    }
}
