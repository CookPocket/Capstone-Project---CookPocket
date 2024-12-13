package com.capstone.cookpocket.view.ui.search

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityMenuSearchBinding
import com.capstone.cookpocket.view.ui.adapter.AdapterActivity
import com.capstone.cookpocket.view.ui.home.HomeViewModel
import com.capstone.cookpocket.view.ui.home.HomeViewModelFactory
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity

class MenuSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuSearchBinding
    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(applicationContext)
    }

    private val cookPocketPagingAdapter by lazy {
        AdapterActivity { storyItem, imageView, textView ->
            val intent = Intent(this, DetailSearchActivity::class.java).apply {
                putExtra("PRODUCT", storyItem)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        setupRecyclerView()

        // Ambil kategori dari intent
        val category = intent.getStringExtra("CATEGORY")

        if (category != null) {
            // Memanggil fungsi fetch berdasarkan kategori
            fetchDataBasedOnCategory(category)
        }

        // Observasi data dari ViewModel
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvSearchBacaResep.apply {
            layoutManager = LinearLayoutManager(this@MenuSearchActivity)
            adapter = cookPocketPagingAdapter
        }
    }

    private fun fetchDataBasedOnCategory(category: String) {
        // Panggil fungsi ViewModel untuk mengambil data berdasarkan kategori
        when (category) {
            "makanan-berat" -> {
                homeViewModel.fetchMakananBerat()
            }
            "makanan-sehat" -> {
                homeViewModel.fetchMakananSehat()
            }
            "makanan-tradisional" -> {
                homeViewModel.fetchMakananTradisional()
            }
            else -> showToast("Kategori tidak dikenali")
        }
    }

    private fun observeViewModel() {
        homeViewModel.stories.observe(this, Observer { stories ->
            stories?.let {
                cookPocketPagingAdapter.submitList(it)
            }
        })

        homeViewModel.errorMessage.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                showToast(it)
            }
        })


    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
