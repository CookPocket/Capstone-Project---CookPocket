package com.capstone.cookpocket.view.ui.search.order

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.cookpocket.databinding.ActivityOrderAmbilSendiriBinding
import com.capstone.cookpocket.view.ui.main.MainActivity

class OrderAmbilSendiriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderAmbilSendiriBinding
    private var quantity: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderAmbilSendiriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data dari Intent
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
            if (productPrice > 0) {
                // Mengirim data produk ke OrderFinishActivity
                val intent = Intent(this, OrderFinishActivity::class.java).apply {
                    putExtra("PRODUCT_NAME", productName)
                    putExtra("PRODUCT_IMAGE", productImage)
                    putExtra("PRODUCT_PRICE", productPrice)
                    putExtra("TOTAL_PRICE", productPrice * quantity)
                    putExtra("USER_ADDRESS", "JL. Urip Sumoeharjo No.91")
                    putExtra("STORE_NAME", "Frozen Food Store")
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Data pesanan tidak valid", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBackAmbilSendiri.setOnClickListener {
            finish()
        }
        binding.cvBtnOrderAntar.setOnClickListener{
            finish()
        }
    }

    // Menampilkan detail pesanan
    private fun displayOrderDetails(productName: String, productImage: String, productPrice: Int) {
        binding.apply {
            tvNamaMakanan.text = productName
            tvHargaMakanan.text = "Rp. $productPrice"
            Glide.with(this@OrderAmbilSendiriActivity)
                .load(productImage)
                .centerCrop()
                .into(imgFood)
        }
    }

    // Memperbarui detail pesanan saat jumlah produk berubah
    private fun updateOrderDetails(productPrice: Int) {
        binding.tvQuantity.text = quantity.toString()
        binding.totalPesanan.text = productPrice.toString()
        binding.totalSemua.text = productPrice.toString()
    }
}
