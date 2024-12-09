package com.capstone.cookpocket.view.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.FragmentSearchBinding
import com.capstone.cookpocket.view.ui.adapter.AdapterActivity
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this, SearchViewModelFactory(requireContext())).get(SearchViewModel::class.java)
    }

    private val adapter by lazy {
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
    }

    private fun setupRecyclerView() {
        binding.rvSearchSiapPesan.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@SearchFragment.adapter

        }
        binding.rvSearchBacaResep.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SearchFragment.adapter
        }
    }

    private fun observeViewModel() {
        // Observasi data dari ViewModel
        searchViewModel.stories.observe(viewLifecycleOwner) { stories ->
            stories?.let {
                adapter.submitList(it)
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
        _binding = null  // Menghindari memory leak dengan null binding
        }
}
