package com.capstone.cookpocket.view.ui.account.change

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.capstone.cookpocket.R
import com.capstone.cookpocket.Network.UserPreferences
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ChangeEmailActivity : AppCompatActivity() {

    private lateinit var edChangeEmail: TextInputLayout
    private lateinit var btnSimpanEmail: Button
    private lateinit var progressBarEmail: ProgressBar
    private lateinit var changeEmailEditText: TextInputEditText
    private lateinit var ivBackEmail: View
    private lateinit var tvCurrentEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)

        // Mengambil referensi komponen UI
        edChangeEmail = findViewById(R.id.ed_change_email)
        btnSimpanEmail = findViewById(R.id.btn_simpan_email)
        progressBarEmail = findViewById(R.id.progresbar_email)
        changeEmailEditText = findViewById(R.id.change_email)
        ivBackEmail = findViewById(R.id.iv_back_email)

        // Menangani klik tombol back
        ivBackEmail.setOnClickListener {
            onBackPressed()
        }

        // Memuat email yang saat ini disimpan
        loadCurrentEmail()

        // Validasi input email saat terjadi perubahan di EditText
        changeEmailEditText.addTextChangedListener {
            val email = it.toString()
            btnSimpanEmail.isEnabled = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        // Tombol Simpan di-click
        btnSimpanEmail.setOnClickListener {
            val newEmail = changeEmailEditText.text.toString()
            if (newEmail.isNotEmpty()) {
                showChangeEmailConfirmationDialog(newEmail)
            } else {
                Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCurrentEmail() {
        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(this@ChangeEmailActivity)
                val currentEmail = userPreferences.userEmail.firstOrNull() ?: "Email tidak ditemukan"
                tvCurrentEmail.text = "Email saat ini: $currentEmail"
            } catch (e: Exception) {
                Toast.makeText(this@ChangeEmailActivity, "Gagal mengambil email saat ini", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Menampilkan dialog konfirmasi untuk mengganti email
    private fun showChangeEmailConfirmationDialog(newEmail: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Ganti Email")
            .setMessage("Apakah Anda yakin ingin mengganti email Anda ke $newEmail?")
            .setPositiveButton("Ya") { _, _ ->
                changeEmail(newEmail)
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss() // Menutup dialog tanpa melakukan perubahan
            }
            .create()

        dialog.show()
    }

    private fun changeEmail(newEmail: String) {
        // Tampilkan ProgressBar
        progressBarEmail.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(this@ChangeEmailActivity)
                // Menyimpan email baru ke SharedPreferences
                userPreferences.saveUserEmail(newEmail)
                // Tampilkan pesan sukses
                Toast.makeText(this@ChangeEmailActivity, "Email berhasil diperbarui", Toast.LENGTH_SHORT).show()

                // Sembunyikan ProgressBar
                progressBarEmail.visibility = View.GONE

                // Kembali ke halaman sebelumnya setelah berhasil mengubah email
                finish()
            } catch (e: Exception) {
                // Tampilkan pesan error jika gagal
                progressBarEmail.visibility = View.GONE
                Toast.makeText(this@ChangeEmailActivity, "Gagal mengubah email, coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
