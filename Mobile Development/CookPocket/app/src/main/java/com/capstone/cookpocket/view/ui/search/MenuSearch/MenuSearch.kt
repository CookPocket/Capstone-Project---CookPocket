package com.capstone.cookpocket.view.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cookpocket.R
import com.capstone.cookpocket.view.ui.home.HomeRepository
import com.capstone.cookpocket.Network.Api.ApiConfig
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.view.ui.home.HomePaging.CookPocketPagingAdapter
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.view.ui.search.MenuSearch.MenuSearchViewModel
import com.capstone.cookpocket.view.ui.search.MenuSearch.MenuSearchViewModelFactory
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MenuSearchActivity : AppCompatActivity() {
    private lateinit var viewModel: MenuSearchViewModel
    private lateinit var adapter: CookPocketPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_search)

        // Tangkap query dari Intent
        val query = intent.getStringExtra("SEARCH_QUERY")

        // Inisialisasi Repository dan ViewModel
        val repository = HomeRepository(ApiConfig.getApiService(), UserPreferences.getInstance(this))
        val factory = MenuSearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MenuSearchViewModel::class.java]

        // Inisialisasi RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rv_search_baca_resep)
        adapter = CookPocketPagingAdapter { story ->
            val intent = Intent(this, DetailSearchActivity::class.java)
            intent.putExtra("story_id", story.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Mulai filter jika query tersedia
        query?.let {
            setupSearchView() // Kirim query awal ke SearchView dan mulai pencarian
        }

        // Observasi data dari ViewModel
        observePagedStories()
    }


    // Observasi data dari ViewModel
    private fun observePagedStories() {
        lifecycleScope.launch {
            viewModel.pagedStories.collectLatest { pagingData ->
                adapter.submitData(pagingData) // Kirim data ke PagingDataAdapter
            }
        }
    }

    // Konfigurasi SearchView
    private fun setupSearchView() {
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchStories(it) // Filter data berdasarkan query
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.searchStories(it) // Filter data secara realtime
                }
                return true
            }
        })
    }
}
