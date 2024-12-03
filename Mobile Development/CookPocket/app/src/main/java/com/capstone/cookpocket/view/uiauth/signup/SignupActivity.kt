package com.capstone.cookpocket.view.uiauth.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.capstone.cookpocket.Network.Api.ApiConfig
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivitySignupBinding
import com.capstone.cookpocket.view.uiauth.AuthRepository
import com.capstone.cookpocket.view.uiauth.login.LoginActivity
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val registerViewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory(AuthRepository(ApiConfig.getApiService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the register button
        binding.btnSignup.setOnClickListener {
            val name = binding.edUsername.text.toString().trim()
            val email = binding.edPasswordSignup.text.toString().trim()
            val password = binding.edPasswordSignup.text.toString().trim()

            // Basic validation checks
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password harus minimal 6 karakter", Toast.LENGTH_SHORT).show()
            } else {
                // Start the registration process and show ProgressBar
                showProgressBar(true)
                registerViewModel.register(name, email, password)
            }
        }

        // Navigate to Login screen if "Login" is clicked
        binding.tvTitleLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        observeRegisterResult()
    }

    private fun observeRegisterResult() {
        lifecycleScope.launch {
            registerViewModel.registerState.collect { response ->
                // Hide ProgressBar and handle response
                showProgressBar(false)

                response?.let {
                    if (!it.error!!) {
                        Toast.makeText(this@SignupActivity, "Register berhasil: ${it.message}", Toast.LENGTH_SHORT).show()
                        // Close SignUp activity and go back to Login
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
