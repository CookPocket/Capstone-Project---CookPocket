package com.capstone.cookpocket.view.ui.account.change

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.capstone.cookpocket.R
import com.capstone.cookpocket.Network.UserPreferences
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var edPasswordOld: TextInputLayout
    private lateinit var edPasswordNew: TextInputLayout
    private lateinit var edPasswordConfirm: TextInputLayout
    private lateinit var btnSavePassword: Button
    private lateinit var progressBarPassword: ProgressBar
    private lateinit var ivBackPassword: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        // Mengambil referensi komponen UI
        edPasswordOld = findViewById(R.id.ed_change_password_old)
        edPasswordNew = findViewById(R.id.ed_change_password_new)
        edPasswordConfirm = findViewById(R.id.ed_change_password_new_confrim)
        btnSavePassword = findViewById(R.id.btn_simpan_password)
        progressBarPassword = findViewById(R.id.progresbar_password)
        ivBackPassword = findViewById(R.id.iv_back_change_password)

        // Menangani klik tombol back
        ivBackPassword.setOnClickListener {
            onBackPressed()
        }

        // Validasi input saat EditText berubah
        val passwordFields = listOf(
            edPasswordOld.editText,
            edPasswordNew.editText,
            edPasswordConfirm.editText
        )
        passwordFields.forEach { field ->
            field?.addTextChangedListener {
                validatePasswords()
            }
        }

        // Tombol Simpan di-click
        btnSavePassword.setOnClickListener {
            val oldPassword = edPasswordOld.editText?.text.toString()
            val newPassword = edPasswordNew.editText?.text.toString()
            val confirmPassword = edPasswordConfirm.editText?.text.toString()

            if (newPassword == confirmPassword) {
                showChangePasswordConfirmationDialog(oldPassword, newPassword)
            } else {
                Toast.makeText(this, "Kata sandi baru tidak cocok", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatePasswords() {
        val oldPassword = edPasswordOld.editText?.text.toString()
        val newPassword = edPasswordNew.editText?.text.toString()
        val confirmPassword = edPasswordConfirm.editText?.text.toString()

        btnSavePassword.isEnabled = oldPassword.isNotEmpty() && newPassword.isNotEmpty() && confirmPassword.isNotEmpty()
    }

    private fun showChangePasswordConfirmationDialog(oldPassword: String, newPassword: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Ganti Kata Sandi")
            .setMessage("Apakah Anda yakin ingin mengganti kata sandi Anda?")
            .setPositiveButton("Ya") { _, _ ->
                changePassword(oldPassword, newPassword)
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss() // Menutup dialog tanpa melakukan perubahan
            }
            .create()

        dialog.show()
    }

    private fun changePassword(oldPassword: String, newPassword: String) {
        // Tampilkan ProgressBar
        progressBarPassword.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val userPreferences = UserPreferences.getInstance(this@ChangePasswordActivity)
                // Simpan kata sandi baru ke SharedPreferences atau API
                // Contoh: userPreferences.saveUserPassword(newPassword)

                // Tampilkan pesan sukses
                Toast.makeText(this@ChangePasswordActivity, "Kata sandi berhasil diperbarui", Toast.LENGTH_SHORT).show()

                // Sembunyikan ProgressBar
                progressBarPassword.visibility = View.GONE

                // Kembali ke halaman sebelumnya
                finish()
            } catch (e: Exception) {
                // Tampilkan pesan error jika gagal
                progressBarPassword.visibility = View.GONE
                Toast.makeText(this@ChangePasswordActivity, "Gagal mengubah kata sandi, coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
