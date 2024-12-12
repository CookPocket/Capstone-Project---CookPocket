package com.capstone.cookpocket.view.ui.search.order

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityOrderAntarBinding
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity

class OrderAntarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderAntarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderAntarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storyName = intent.getStringExtra("STORY_NAME") ?: "No Title"
        val storyDescription = intent.getStringExtra("STORY_DESCRIPTION") ?: "No Description"
        val storyPhoto = intent.getStringExtra("STORY_PHOTO") ?: ""

        // Menampilkan detail cerita di UI
        displayStoryDetails(storyName, storyDescription, storyPhoto)

        binding.ivBackAntar .setOnClickListener {
            val intent = Intent(this, DetailSearchActivity::class.java)
            intent.putExtra("STORY_NAME", storyName)
            intent.putExtra("STORY_DESCRIPTION", storyDescription)
            intent.putExtra("STORY_PHOTO", storyPhoto)
            startActivity(intent)
        }

        // Tombol untuk ambil sendiri
        binding.cvBtnAmbilSendiri.setOnClickListener {
            val intent = Intent(this, OrderAmbilSendiriActivity::class.java)
            intent.putExtra("STORY_NAME", storyName)
            intent.putExtra("STORY_DESCRIPTION", storyDescription)
            intent.putExtra("STORY_PHOTO", storyPhoto)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun displayStoryDetails(name: String, description: String, photoUrl: String) {
        binding.apply {

            supportActionBar?.title = name

            // Tampilkan data
            // Data dummy
            val harga = 100000
            val biayaPengirimanPersen = 0.10 // 10%

// Menghitung biaya pengiriman
            val biayaPengiriman = harga * biayaPengirimanPersen

// Menghitung total pesanan
            val totalPesanan = harga + biayaPengiriman

// Menampilkan data di tampilan
            tvNamaMakanan.text = "Nasi Goreng Special"
            tvUsernameUser.text = "John Doe" // Data pengguna dummy
            tvNoTelpUser.text = "081212000637"
            tvAlamatUser.text = "Jl. Contoh Alamat No. 123"

            tvTotalPesanan.text = "Rp. ${totalPesanan.toInt()}"

// Menampilkan gambar menggunakan Glide dengan placeholder
            Glide.with(this@OrderAntarActivity)
                .load("https://example.com/photo_url.jpg")
                .centerCrop()
                .into(imgFood) // ImageView untuk foto makanan
        }
    }
}
