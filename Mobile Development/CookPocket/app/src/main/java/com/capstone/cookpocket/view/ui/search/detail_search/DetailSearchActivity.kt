package com.capstone.cookpocket.view.ui.search.detail_search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityDetailSearchBinding

class DetailSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data dari Intent
        val storyName = intent.getStringExtra("STORY_NAME")
        val storyDescription = intent.getStringExtra("STORY_DESCRIPTION")
        val storyPhoto = intent.getStringExtra("STORY_PHOTO")

        // Tampilkan data ke UI
        if (storyName != null && storyPhoto != null) {
            displayStoryDetails(storyName, storyDescription, storyPhoto)
        } else {
            showError("Story data not found.")
            finish()
        }

        setupActionBar()
    }

    private fun displayStoryDetails(name: String, description: String?, photoUrl: String) {
        binding.apply {
            // Set title di ActionBar
            supportActionBar?.title = name

            // Menampilkan data
            tvJudul.text = name
            tvDeskripsiBahan.text = HtmlCompat.fromHtml(description ?: "No Description", HtmlCompat.FROM_HTML_MODE_LEGACY)

            Glide.with(this@DetailSearchActivity)
                .load(photoUrl)
                .centerCrop()
                .into(ivMainImage)
        }
    }

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

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
