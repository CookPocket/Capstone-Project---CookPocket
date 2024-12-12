package com.capstone.cookpocket.view.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.FragmentSearchBinding
import com.capstone.cookpocket.view.ui.adapter.AdapterActivity
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this, SearchViewModelFactory(requireContext())).get(SearchViewModel::class.java)
    }

    private val CookPocketPagingAdapter by lazy {
        AdapterActivity { storyItem, imageView, textView ->
            val intent = Intent(requireContext(), DetailSearchActivity::class.java).apply {
                putExtra("STORY_NAME", storyItem.name)
                putExtra("STORY_DESCRIPTION", storyItem.description)
                putExtra("STORY_PHOTO", storyItem.photoUrl)
            }
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Konfigurasi RecyclerView
        setupRecyclerView()

        // Observasi data dari ViewModel
        observeViewModel()

        // Ambil data cerita dari ViewModel
        searchViewModel.fetchStories()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    // Pindah ke MenuSearchActivity dengan query
                    val intent = Intent(requireContext(), MenuSearchActivity::class.java)
                    intent.putExtra("SEARCH_QUERY", it) // Kirim query ke activity
                    startActivity(intent)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Anda bisa menangani perubahan teks di sini jika perlu
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvSearchSiapPesan.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@SearchFragment.CookPocketPagingAdapter

        }
        binding.rvSearchBacaResep.apply {
            // Ganti dengan GridLayoutManager dan tentukan jumlah kolom
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = CookPocketPagingAdapter
        }
    }

    private fun observeViewModel() {
        // Observasi data dari ViewModel
        searchViewModel.stories.observe(viewLifecycleOwner) { stories ->
            stories?.let {
                CookPocketPagingAdapter.submitList(it)
            }
        }

        // Menangani error dari ViewModel
        searchViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { showToast(it) }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Menghindari memory leak dengan null binding
        }
}
