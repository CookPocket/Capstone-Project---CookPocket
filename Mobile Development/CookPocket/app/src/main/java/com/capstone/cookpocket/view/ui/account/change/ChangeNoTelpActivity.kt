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

class ChangeNoTelpActivity : AppCompatActivity() {

    private lateinit var edChangeNoTelp: TextInputLayout
    private lateinit var btnSimpanNoTelp: Button
    private lateinit var progressBarNoTelp: ProgressBar
    private lateinit var changeNoTelpEditText: TextInputEditText
    private lateinit var ivBackNoTelp: View
    private lateinit var tvCurrentNoTelp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_no_telp)


        // Mengambil referensi komponen UI
        edChangeNoTelp = findViewById(R.id.ed_change_notelp)
        btnSimpanNoTelp = findViewById(R.id.btn_simpan_notelp)
        progressBarNoTelp = findViewById(R.id.progresbar_notelp)
        changeNoTelpEditText = findViewById(R.id.change_notelp)
        ivBackNoTelp = findViewById(R.id.iv_back_change_notelp)
        tvCurrentNoTelp = findViewById(R.id.desc_notelp)

        // Menangani klik tombol back
        ivBackNoTelp.setOnClickListener {
            onBackPressed()
        }

        // Memuat nomor telepon yang saat ini disimpan
        loadCurrentNoTelp()

        // Validasi input nomor telepon saat terjadi perubahan di EditText
        changeNoTelpEditText.addTextChangedListener {
            val noTelp = it.toString()
            btnSimpanNoTelp.isEnabled = noTelp.isNotEmpty() && android.util.Patterns.PHONE.matcher(noTelp).matches()
        }

        // Tombol Simpan di-click
        btnSimpanNoTelp.setOnClickListener {
            val newNoTelp = changeNoTelpEditText.text.toString()
            if (newNoTelp.isNotEmpty()) {
                showChangeNoTelpConfirmationDialog(newNoTelp)
            } else {
                Toast.makeText(this, "Nomor telepon tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCurrentNoTelp() {
        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(this@ChangeNoTelpActivity)
//                val currentNoTelp = userPreferences.userNoTelp.firstOrNull() ?: "Nomor telepon tidak ditemukan"
//                tvCurrentNoTelp.text = "Nomor telepon saat ini: $currentNoTelp"
            } catch (e: Exception) {
                Toast.makeText(this@ChangeNoTelpActivity, "Gagal mengambil nomor telepon saat ini", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Menampilkan dialog konfirmasi untuk mengganti nomor telepon
    private fun showChangeNoTelpConfirmationDialog(newNoTelp: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Ganti Nomor Telepon")
            .setMessage("Apakah Anda yakin ingin mengganti nomor telepon Anda ke $newNoTelp?")
            .setPositiveButton("Ya") { _, _ ->
                changeNoTelp(newNoTelp)
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss() // Menutup dialog tanpa melakukan perubahan
            }
            .create()

        dialog.show()
    }

    private fun changeNoTelp(newNoTelp: String) {
        // Tampilkan ProgressBar
        progressBarNoTelp.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(this@ChangeNoTelpActivity)
                // Menyimpan nomor telepon baru ke SharedPreferences
//                userPreferences.saveUserNoTelp(newNoTelp)
                // Tampilkan pesan sukses
                Toast.makeText(this@ChangeNoTelpActivity, "Nomor telepon berhasil diperbarui", Toast.LENGTH_SHORT).show()

                // Sembunyikan ProgressBar
                progressBarNoTelp.visibility = View.GONE

                // Kembali ke halaman sebelumnya setelah berhasil mengubah nomor telepon
                finish()
            } catch (e: Exception) {
                // Tampilkan pesan error jika gagal
                progressBarNoTelp.visibility = View.GONE
                Toast.makeText(this@ChangeNoTelpActivity, "Gagal mengubah nomor telepon, coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
