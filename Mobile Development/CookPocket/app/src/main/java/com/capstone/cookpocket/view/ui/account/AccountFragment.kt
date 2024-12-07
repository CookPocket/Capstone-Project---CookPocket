package com.capstone.cookpocket.view.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.appcompat.app.AlertDialog
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.R
import com.capstone.cookpocket.view.uiauth.Login.LoginActivity
import kotlinx.coroutines.launch

class AccountFragment : Fragment() {

    companion object {
        fun newInstance() = AccountFragment()
    }

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = inflater.inflate(R.layout.fragment_account, container, false)

        // Inisialisasi button logout
        val btnLogout: ImageView = binding.findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        return binding
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                logout()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun logout() {
        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(requireContext())
                userPreferences.clearToken()
                userPreferences.clearUserName()
                Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()

                // Arahkan pengguna ke LoginActivity setelah logout
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish() // Menutup aktivitas saat ini
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Logout gagal, coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
