package com.capstone.cookpocket.view.ui.search.order

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityOrderFinishBinding
import com.capstone.cookpocket.view.ui.main.MainActivity

class OrderFinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOrderFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productName = intent.getStringExtra("PRODUCT_NAME") ?: "Produk Tidak Dikenal"
        val productImage = intent.getStringExtra("PRODUCT_IMAGE") ?: ""
        val productPrice = intent.getIntExtra("PRODUCT_PRICE", 0)
        val totalOrderPrice = intent.getIntExtra("TOTAL_PRICE", 0)
        val deliveryCost = intent.getIntExtra("DELIVERY_COST", 0)
        val userAddress = intent.getStringExtra("USER_ADDRESS") ?: "Alamat tidak tersedia"
        val storeName = intent.getStringExtra("STORE_NAME") ?: "Toko tidak tersedia"

        displayOrderDetails(productName, productImage, productPrice, totalOrderPrice, deliveryCost)
        displayAdditionalInfo(userAddress, storeName)

        binding.btnPesananSelesai.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayOrderDetails(productName: String, productImage: String, productPrice: Int, totalOrderPrice: Int, deliveryCost: Int) {
        binding.apply {
            tvNamaMakanan.text = productName
            tvHargaMakanan.text = "Rp. $productPrice"
            Glide.with(this@OrderFinishActivity)
                .load(productImage)
                .centerCrop()
                .into(imgFood)

            totalPesanan.text = "Rp. $totalOrderPrice"
            totalSemua.text = "Rp. ${totalOrderPrice + deliveryCost}"
            totalBiayaPengiriman.text = "Rp. $deliveryCost"
        }
    }

    private fun displayAdditionalInfo(userAddress: String, storeName: String) {
        binding.apply {
            tvAlamatUser.text = userAddress
            tvTokoMitra.text = storeName
        }
    }
}
