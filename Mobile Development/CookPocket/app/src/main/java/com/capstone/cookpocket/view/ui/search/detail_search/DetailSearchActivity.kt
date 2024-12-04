package com.capstone.cookpocket.view.ui.search.detail_search

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.cookpocket.databinding.ActivityDetailSearchBinding

class DetailSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aktifkan edge-to-edge mode untuk mendukung tampilan penuh
        enableEdgeToEdge()

        // Inisialisasi ViewBinding
        binding = ActivityDetailSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent
        val name = intent.getStringExtra("STORY_NAME")
        val description = intent.getStringExtra("STORY_DESCRIPTION")
        val photoUrl = intent.getStringExtra("STORY_PHOTO")

        // Set data ke view
        binding.tvJudul.text = name ?: "Nama tidak tersedia"
        binding.tvDeskripsiBahan.text = description ?: "Deskripsi tidak tersedia"

        // Gunakan Glide untuk memuat gambar
        Glide.with(this)
            .load(photoUrl)
            .into(binding.ivMainImage)
    }
}