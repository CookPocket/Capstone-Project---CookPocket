package com.capstone.cookpocket.view.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.databinding.FragmentAccountBinding
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import androidx.appcompat.app.AlertDialog
import com.capstone.cookpocket.view.ui.account.change.ChangeAddresActivity
import com.capstone.cookpocket.view.ui.account.change.ChangeEmailActivity
import com.capstone.cookpocket.view.ui.account.change.ChangeNoTelpActivity
import com.capstone.cookpocket.view.ui.account.change.ChangePasswordActivity
import com.capstone.cookpocket.view.ui.account.change.ChangeUsernameActivity
import com.capstone.cookpocket.view.ui.store.StoreActivity
import com.capstone.cookpocket.view.uiauth.Login.LoginActivity

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)

        // Memanggil fungsi untuk memuat data pengguna
        loadUserData()

        // Logout button functionality
        binding.btnLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        binding.llBtnShop.setOnClickListener{
            val intent = Intent(requireContext(), StoreActivity::class.java)
            startActivity(intent)
        }

        // Ubah nama username
        binding.arrowGantiUsername.setOnClickListener {
            val intent = Intent(requireContext(), ChangeUsernameActivity::class.java)
            startActivity(intent)
        }

        binding.arrowGantiEmail.setOnClickListener {
            val intent = Intent(requireContext(), ChangeEmailActivity::class.java)
            intent.putExtra("EMAIL", binding.tvGmailAccount.text.toString())
            startActivity(intent)

        }

        binding.arrowGantiNotelp.setOnClickListener {
            val intent = Intent(requireContext(), ChangeNoTelpActivity::class.java)
            startActivity(intent)
        }

        binding.arrowGantiSandi.setOnClickListener {
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }
        binding.arrowAlamat.setOnClickListener{
            val intent = Intent(requireContext(), ChangeAddresActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    // Fungsi untuk memuat data pengguna
    private fun loadUserData() {
        lifecycleScope.launch {
            val userPreferences = UserPreferences.getInstance(requireContext())

            // Mengambil data dari preferences
            val name = userPreferences.userName.firstOrNull() ?: "Nama tidak ditemukan"
            val email = userPreferences.userEmail.firstOrNull() ?: "Email tidak ditemukan"
            val imageUrl = userPreferences.userImageUrl.firstOrNull() ?: "https://example.com/default.jpg"

            // Tampilkan data ke UI
            binding.tvUsernameAccount.text = name
            binding.tvGmailAccount.text = email

            // Menggunakan Glide untuk menampilkan gambar profil
            Glide.with(this@AccountFragment)
                .load(imageUrl)
                .placeholder(com.capstone.cookpocket.R.drawable.logo_cook_pocket) // Gambar placeholder
                .error(com.capstone.cookpocket.R.drawable.logo_cook_pocket) // Gambar error jika gagal
                .into(binding.imgAccount) // Pastikan binding.imgAccount sesuai dengan ImageView di layout
        }
    }

    // Menampilkan dialog konfirmasi logout
    private fun showLogoutConfirmationDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ -> logout() }
            .setNegativeButton("Tidak") { dialog, _ -> dialog.dismiss() }
            .create()
        dialog.show()
    }

    // Proses logout
    private fun logout() {
        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(requireContext())

                // Hapus data pengguna saat logout
                userPreferences.clearToken()
                userPreferences.clearUserName()
                userPreferences.clearUserEmail()

                Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()

                // Navigasi ke LoginActivity setelah logout
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish() // Menutup aktivitas ini
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Logout gagal, coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Menghindari memory leak dengan membebaskan binding
    }
}
