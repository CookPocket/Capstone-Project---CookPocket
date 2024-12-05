package com.capstone.cookpocket.view.ui.search.detail_search

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailSearchActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityDetailSearchBinding
//    private lateinit var viewModel: FavoriteViewModel
//    private lateinit var eventId: String
//    private lateinit var fabFavorite: FloatingActionButton
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Aktifkan edge-to-edge mode untuk mendukung tampilan penuh
//        enableEdgeToEdge()
//
//        // Inisialisasi ViewBinding
//        binding = ActivityDetailSearchBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Inisialisasi ViewModel
//        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
//
//        // Ambil data dari Intent
//        val name = intent.getStringExtra("STORY_NAME")
//        val description = intent.getStringExtra("STORY_DESCRIPTION")
//        val photoUrl = intent.getStringExtra("STORY_PHOTO")
//        eventId = intent.getStringExtra("EVENT_ID") ?: "Unknown"
//
//        // Set data ke view
//        binding.tvJudul.text = name ?: "Nama tidak tersedia"
//        binding.tvDeskripsiBahan.text = description ?: "Deskripsi tidak tersedia"
//        Glide.with(this)
//            .load(photoUrl)
//            .into(binding.ivMainImage)
//
//        // Tombol kembali
//        binding.ivBack.setOnClickListener {
//            onBackPressed()
//        }
//
//        // Setup tombol favorit
//        setupFavoriteButton(eventId)
//
//        // Pindah ke fragment favorit (opsional)
//        binding.ivFavorite.setOnClickListener {
//            val intent = Intent(this, FavoriteFragment::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun setupFavoriteButton(eventId: String) {
//        viewModel.getFavoriteEventById(eventId).observe(this) { favoriteEvent ->
//            if (favoriteEvent == null) {
//                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
//            } else {
//                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
//            }
//
//            binding.fabFavorite.setOnClickListener {
//                if (favoriteEvent == null) {
//                    val newFavorite = FavEvent(
//                        id = eventId,
//                        name = binding.tvJudul.text.toString(),
//                        mediaCover = intent.getStringExtra("STORY_PHOTO") ?: ""
//                    )
//                    viewModel.insertFavorite(newFavorite)
//                    binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
//                    showToast("Event added to favorites")
//                } else {
//                    viewModel.deleteFavorite(favoriteEvent)
//                    binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
//                    showToast("Event removed from favorites")
//                }
//            }
//        }
//    }
}
