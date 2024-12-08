package com.capstone.cookpocket.view.uiauth.SignUp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstone.cookpocket.Network.Api.ApiConfig
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivitySignupBinding
import com.capstone.cookpocket.view.uiauth.AuthRepository
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val registerViewModel: SignupViewModel by viewModels {
        SignupViewModelFactory(AuthRepository(ApiConfig.getApiService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hubungkan konfirmasi password dengan password
        binding.edConfirmPassword.setPasswordField(binding.edPassword)

        // Set click listener untuk tombol daftar
        binding.btnSignup.setOnClickListener {
            val name = binding.edUsername.text.toString().trim()
            val email = binding.edEmail.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()
            val confirmPassword = binding.edConfirmPassword.text.toString().trim()

            // Validasi form
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
            } else {
                // Mulai proses registrasi dan tampilkan ProgressBar
                showProgressBar(true)
                registerViewModel.register(name, email, password)
            }
        }

        observeRegisterResult()
    }

    private fun observeRegisterResult() {
        lifecycleScope.launch {
            registerViewModel.registerState.collect { response ->
                // Sembunyikan ProgressBar dan tangani respons
                showProgressBar(false)

                response?.let {
                    if (!it.error!!) {
                        Toast.makeText(this@SignupActivity, "Register berhasil: ${it.message}", Toast.LENGTH_SHORT).show()
                        // Tutup aktivitas SignUp dan kembali ke Login
                        finish()
                    } else {
                        Toast.makeText(this@SignupActivity, "Register gagal: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            binding.progressBar.visibility = android.view.View.VISIBLE
        } else {
            binding.progressBar.visibility = android.view.View.GONE
        }
    }
}
