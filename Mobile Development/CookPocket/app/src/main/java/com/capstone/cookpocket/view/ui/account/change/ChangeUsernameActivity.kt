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

class ChangeUsernameActivity : AppCompatActivity() {

    private lateinit var edChangeUsername: TextInputLayout
    private lateinit var changeUsernameEditText: TextInputEditText
    private lateinit var btnSimpanUsername: Button
    private lateinit var progressBarUsername: ProgressBar
    private lateinit var ivBackUsername: View
    private lateinit var tvDescUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_username)

        // Mengambil referensi komponen UI
        edChangeUsername = findViewById(R.id.ed_change_username)
        changeUsernameEditText = findViewById(R.id.change_username)
        btnSimpanUsername = findViewById(R.id.btn_simpan_username)
        progressBarUsername = findViewById(R.id.progresbar_username)
        ivBackUsername = findViewById(R.id.iv_back_username)
        tvDescUsername = findViewById(R.id.desc_username)

        // Menangani tombol back
        ivBackUsername.setOnClickListener {
            onBackPressed()
        }

        // Validasi input username saat terjadi perubahan
        changeUsernameEditText.addTextChangedListener {
            val username = it.toString()
            btnSimpanUsername.isEnabled = username.isNotEmpty() && username.length <= 10
        }

        // Tombol Simpan di-click
        btnSimpanUsername.setOnClickListener {
            val newUsername = changeUsernameEditText.text.toString()
            if (newUsername.isNotEmpty() && newUsername.length <= 10) {
                showChangeUsernameConfirmationDialog(newUsername)
            } else {
                Toast.makeText(this, "Username tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Menampilkan dialog konfirmasi untuk mengganti username
    private fun showChangeUsernameConfirmationDialog(newUsername: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Ganti Username")
            .setMessage("Apakah Anda yakin ingin mengganti username Anda ke \"$newUsername\"?")
            .setPositiveButton("Ya") { _, _ ->
                changeUsername(newUsername)
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun changeUsername(newUsername: String) {
        // Tampilkan ProgressBar
        progressBarUsername.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(this@ChangeUsernameActivity)
                // Menyimpan username baru ke SharedPreferences
                userPreferences.saveUserName(newUsername)
                // Tampilkan pesan sukses
                Toast.makeText(this@ChangeUsernameActivity, "Username berhasil diperbarui", Toast.LENGTH_SHORT).show()

                // Sembunyikan ProgressBar
                progressBarUsername.visibility = View.GONE
                finish()
            } catch (e: Exception) {
                // Tampilkan pesan error jika gagal
                progressBarUsername.visibility = View.GONE
                Toast.makeText(this@ChangeUsernameActivity, "Gagal mengubah username, coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
