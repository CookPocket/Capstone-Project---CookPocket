package com.capstone.cookpocket.view.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.FragmentHomeBinding
import com.capstone.cookpocket.view.ui.adapter.AdapterActivity
import com.capstone.cookpocket.view.ui.home.cart.CartActivity
import com.capstone.cookpocket.view.ui.home.notif.NotificationActivity
import com.capstone.cookpocket.view.ui.search.MenuSearchActivity
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity
import com.capstone.cookpocket.view.uiauth.Login.LoginActivity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            HomeViewModelFactory(requireContext())
        ).get(HomeViewModel::class.java)
    }

    private val cookPocketPagingAdapter by lazy {
        AdapterActivity { storyItem, imageView, textView ->
            val intent = Intent(requireContext(), DetailSearchActivity::class.java).apply {
                putExtra("PRODUCT", storyItem)
            }
            startActivity(intent)
        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Periksa token dan navigasikan ke LoginActivity jika tidak ditemukan
        checkTokenAndNavigate()

        // Konfigurasi RecyclerView
        setupRecyclerView()

        // Observasi data dari ViewModel
        observeViewModel()

        homeViewModel.fetchMakananTradisional() // Jika ingin memuat data awal (opsional)

        binding.ivKeranjang.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }

        binding.ivNotifikasi.setOnClickListener {
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }

        binding.searchBarHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        // Tombol kategori makanan berat, tradisional, dan sehat
        binding.cvBtnMakananBerat.setOnClickListener {
            navigateToMenuSearchActivity("makanan-berat")
        }
        binding.cvBtnMakananTradisional.setOnClickListener {
            navigateToMenuSearchActivity("makanan-tradisional")
        }
        binding.cvBtnMakananSehat.setOnClickListener {
            navigateToMenuSearchActivity("makanan-sehat")
        }
    }

    private fun checkTokenAndNavigate() {
        lifecycleScope.launch {
            val userPreferences = UserPreferences.getInstance(requireContext())
            val token = userPreferences.token.firstOrNull()

            // Jika token tidak ada, arahkan ke LoginActivity
            if (token.isNullOrEmpty()) {
                Log.d("HomeFragment", "Token tidak ditemukan. Arahkan ke Login.")
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish() // Pastikan aktivitas saat ini ditutup
            } else {
                // Ambil username dan set ke UI
                val userName = userPreferences.userName.firstOrNull()
                binding.userName.text = userName ?: ""
            }
        }
    }

    private fun setupRecyclerView() {
        binding.favoriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cookPocketPagingAdapter
        }
    }

    private fun observeViewModel() {
        homeViewModel.stories.observe(viewLifecycleOwner) { stories ->
            if (stories != null) {
                cookPocketPagingAdapter.submitList(stories)
            }
        }

        homeViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { showToast(it) }
        }
    }

    // Fungsi untuk menampilkan toast
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // Fungsi untuk menavigasi ke MenuSearchActivity dengan kategori yang dipilih
    private fun navigateToMenuSearchActivity(category: String) {
        val intent = Intent(requireContext(), MenuSearchActivity::class.java).apply {
            putExtra("CATEGORY", category) // Mengirim kategori ke MenuSearchActivity
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
