package com.capstone.cookpocket.view.uiauth.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstone.cookpocket.Network.Api.ApiConfig
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityLoginBinding
import com.capstone.cookpocket.view.ui.main.MainActivity
import com.capstone.cookpocket.view.uiauth.AuthRepository
import com.capstone.cookpocket.view.uiauth.SignUp.SignupActivity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userPreferences: UserPreferences

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AuthRepository(ApiConfig.getApiService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences.getInstance(this)

        lifecycleScope.launch {
            val token = userPreferences.token.firstOrNull()
            if (!token.isNullOrEmpty()) {
                navigateToHome()
            }
        }

        binding.tvBtnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmail.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
            } else {
                showProgressBar(true)
                loginViewModel.login(email, password)
            }
        }

        observeLoginState()
    }

    private fun observeLoginState() {
        lifecycleScope.launchWhenStarted {
            loginViewModel.loginState.collect { response ->
                response?.let {
                    // Menyembunyikan ProgressBar setelah login selesai
                    showProgressBar(false)

                    if (!it.error) {
                        Toast.makeText(this@LoginActivity, "Login berhasil: ${it.loginResult.name}", Toast.LENGTH_SHORT).show()

                        val token = it.loginResult.token
                        val userId = it.loginResult.userId
                        Log.d("LoginActivity", "Token dari API: $token, User ID: $userId")  // Menampilkan userId di Log

                        // Simpan token, userId, dan username ke UserPreferences
                        if (!token.isNullOrEmpty()) {
                            userPreferences.saveToken(token)
                            userPreferences.saveUserId(userId)
                            userPreferences.saveUserName(it.loginResult.name)
                            Log.d("LoginActivity", "Token dan User ID berhasil disimpan")
                        } else {
                            Log.e("LoginActivity", "Token atau User ID kosong.")
                        }
                        navigateToHome()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login gagal: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Fungsi untuk menampilkan atau menyembunyikan ProgressBar
    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
