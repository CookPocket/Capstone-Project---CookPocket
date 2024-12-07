package com.capstone.cookpocket.view.ui.search.detail_search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstone.cookpocket.R

import com.capstone.cookpocket.databinding.ActivityDetailSearchBinding
import com.capstone.cookpocket.view.ui.search.order.OrderAntarActivity
import kotlinx.coroutines.launch

class DetailSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSearchBinding
//    private lateinit var viewModel: DetailSearchViewModel
    private lateinit var fabFavorite: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // Inisialisasi ViewModel
//        val dao: FavoriteItemDao = FavoriteItemDatabase.getDatabase(this).favoriteItemDao()
//        viewModel = ViewModelProvider(this, DetailSearchViewModelFactory(dao)).get(DetailSearchViewModel::class.java)

        // Ambil data dari Intent
        val storyId = intent.getStringExtra("STORY_ID") ?: ""
        val storyName = intent.getStringExtra("STORY_NAME") ?: "No Title"
        val storyDescription = intent.getStringExtra("STORY_DESCRIPTION") ?: "No Description"
        val storyPhoto = intent.getStringExtra("STORY_PHOTO") ?: ""

        // Tampilkan detail cerita
        displayStoryDetails(storyName, storyDescription, storyPhoto)

        // Setup ActionBar
        setupActionBar()

        // Setup tombol favorite
        fabFavorite = findViewById(R.id.iv_favorite)
//        setupFavoriteButton(storyId, storyName, storyDescription, storyPhoto)

        binding.buttonPesan.setOnClickListener {
            val intent = Intent(this, OrderAntarActivity::class.java)
            intent.putExtra("STORY_ID", storyId)
            intent.putExtra("STORY_NAME", storyName)
            intent.putExtra("STORY_DESCRIPTION", storyDescription)
            intent.putExtra("STORY_PHOTO", storyPhoto)
            startActivity(intent)
        }
        binding.ivBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun displayStoryDetails(name: String, description: String, photoUrl: String) {
        binding.apply {
            // Set title di ActionBar
            supportActionBar?.title = name

            // Tampilkan data
            tvJudul.text = name
            tvDeskripsiBahan.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)

            // Muat gambar dengan Glide
            Glide.with(this@DetailSearchActivity)
                .load(photoUrl)
                .centerCrop()
                .placeholder(R.drawable.rounded_image_background) // Placeholder
                .into(ivMainImage)
        }
    }

//    private fun setupFavoriteButton(storyId: String, storyName: String, storyDescription: String, storyPhoto: String) {
//        viewModel.getFavoriteById(storyId).observe(this) { favoriteItem ->
//            if (favoriteItem == null || favoriteItem.storyId != storyId) {
//                fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
//            } else {
//                fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
//            }
//            fabFavorite.setOnClickListener {
//                if (favoriteItem == null) {
//                    val newFavorite = FavoriteItem(
//                        storyId = storyId,
//                        name = storyName,
//                        description = storyDescription,
//                        photoUrl = storyPhoto
//                    )
//                    lifecycleScope.launch {
//                        viewModel.insertFavorite(newFavorite)
//                        showToast("$storyName added to favorites!")
//                    }
//                } else {
//                    lifecycleScope.launch {
//                        viewModel.deleteFavorite(favoriteItem)
//                        showToast("$storyName removed from favorites!")
//                    }
//                }
//            }
//        }
//    }




    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
