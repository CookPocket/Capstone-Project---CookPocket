package com.capstone.cookpocket.view.ui.search.order

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.cookpocket.databinding.ActivityOrderAmbilSendiriBinding
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity

class OrderAmbilSendiriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderAmbilSendiriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderAmbilSendiriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data yang dikirim dari Intent
        val storyName = intent.getStringExtra("STORY_NAME") ?: "No Title"
        val storyDescription = intent.getStringExtra("STORY_DESCRIPTION") ?: "No Description"
        val storyPhoto = intent.getStringExtra("STORY_PHOTO") ?: ""

        // Menampilkan detail cerita di UI
        displayStoryDetails(storyName, storyDescription, storyPhoto)

        // Tombol untuk kembali ke halaman sebelumnya
        binding.ivBackAmbilSendiri.setOnClickListener {
            val intent = Intent(this, DetailSearchActivity::class.java)
            intent.putExtra("STORY_NAME", storyName)
            intent.putExtra("STORY_DESCRIPTION", storyDescription)
            intent.putExtra("STORY_PHOTO", storyPhoto)
            startActivity(intent)
        }
        binding.cvBtnOrderAntar.setOnClickListener{
            onBackPressed()
        }
    }

    private fun displayStoryDetails(name: String, description: String, photoUrl: String) {
        binding.apply {
            // Set title di ActionBar
            supportActionBar?.title = name

            // Menampilkan data
            tvNamaMakanan.text = name

            // Memuat gambar dengan Glide
            Glide.with(this@OrderAmbilSendiriActivity)
                .load(photoUrl)
                .centerCrop()
                .into(imgFood)  // ImageView untuk menampilkan gambar cerita
        }
    }
}
