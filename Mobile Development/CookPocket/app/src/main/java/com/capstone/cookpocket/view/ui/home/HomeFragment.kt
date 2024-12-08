package com.capstone.cookpocket.view.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private val cookPocketHomeAdapter by lazy {
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

        // Ambil data cerita dari ViewModel
        homeViewModel.fetchStories()

        binding.ivKeranjang.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }

        binding.ivNotifikasi.setOnClickListener {
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
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
            adapter = cookPocketHomeAdapter
        }
    }

    private fun observeViewModel() {
        homeViewModel.stories.observe(viewLifecycleOwner) { stories ->
            if (stories != null) {
                cookPocketHomeAdapter.submitList(stories)
            }
        }

        homeViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { showToast(it) }
        }
    }

    private fun logout() {
        lifecycleScope.launch {
            val userPreferences = UserPreferences.getInstance(requireContext())
            userPreferences.clearToken()
            userPreferences.clearUserName()
            Log.d("HomeFragment", "Token dan nama pengguna berhasil dihapus")

            Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()

            // Setelah logout, arahkan ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Menutup aktivitas saat ini
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
