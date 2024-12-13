//package com.capstone.cookpocket.view.ui.account
//
//import android.content.Intent
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.appcompat.app.AlertDialog
//import com.capstone.cookpocket.Network.UserPreferences
//import com.capstone.cookpocket.R
//import com.capstone.cookpocket.databinding.FragmentAccountBinding
//import com.capstone.cookpocket.view.ui.account.change.ChangeAddresActivity
//import com.capstone.cookpocket.view.ui.account.change.ChangeEmailActivity
//import com.capstone.cookpocket.view.ui.account.change.ChangeNoTelpActivity
//import com.capstone.cookpocket.view.ui.account.change.ChangePasswordActivity
//import com.capstone.cookpocket.view.ui.account.change.ChangeUsernameActivity
//import com.capstone.cookpocket.view.ui.store.HomeStore.StoreActivity
//import com.capstone.cookpocket.view.uiauth.Login.LoginActivity
//import kotlinx.coroutines.launch
//
//class AccountFragment : Fragment() {
//
//    private var _binding: FragmentAccountBinding? = null
//    private val binding get() = _binding!!
//
//    private val viewModel: AccountViewModel by viewModels()
//
//    companion object {
//        fun newInstance() = AccountFragment()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentAccountBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Inisialisasi button logout
//        binding.btnLogout.setOnClickListener {
//            showLogoutConfirmationDialog()
//        }
//
//        // Inisialisasi elemen lain di layout (contoh pengaturan klik listener)
//        binding.arrowGantiUsername.setOnClickListener {
//            val intent = Intent(requireContext(), ChangeUsernameActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.arrowGantiEmail.setOnClickListener {
//            val intent = Intent(requireContext(), ChangeEmailActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.arrowGantiNotelp.setOnClickListener {
//            val intent = Intent(requireContext(), ChangeNoTelpActivity::class.java)
//            startActivity(intent)
//        }
//        binding.arrowGantiSandi.setOnClickListener {
//            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.arrowAlamat.setOnClickListener {
//            val intent = Intent(requireContext(), ChangeAddresActivity::class.java)
//            startActivity(intent)
//        }
//        binding.llBtnShop.setOnClickListener {
//            val intent = Intent(requireContext(), StoreActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun showLogoutConfirmationDialog() {
//        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_logout, null)
//
//        val dialog = AlertDialog.Builder(requireContext())
//            .setView(customView)
//            .create()
//
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        customView.findViewById<TextView>(R.id.tvTitle).text = "Konfirmasi Logout"
//        customView.findViewById<TextView>(R.id.tvMessage).text = "Apakah Anda yakin ingin keluar?"
//
//        customView.findViewById<Button>(R.id.btnYes).setOnClickListener {
//            dialog.dismiss()
//            logout()
//        }
//
//        customView.findViewById<Button>(R.id.btnNo).setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
//
//    private fun logout() {
//        lifecycleScope.launch {
//            try {
//                val userPreferences = UserPreferences.getInstance(requireContext())
//                userPreferences.clearToken()
//                userPreferences.clearUserName()
//                Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()
//
//                // Arahkan pengguna ke LoginActivity setelah logout
//                val intent = Intent(requireContext(), LoginActivity::class.java)
//                startActivity(intent)
//                requireActivity().finish() // Menutup aktivitas saat ini
//            } catch (e: Exception) {
//                Toast.makeText(requireContext(), "Logout gagal, coba lagi", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
