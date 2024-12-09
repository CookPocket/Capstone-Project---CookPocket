package com.capstone.cookpocket.view.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.cookpocket.R
import com.capstone.cookpocket.view.uiauth.Login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Menyesuaikan padding sistem
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ambil elemen-elemen dari XML
        val logoImageView = findViewById<ImageView>(R.id.logo_app)
        val backgroundImageView = findViewById<ImageView>(R.id.bg_logo)
        val appNameTextView = findViewById<TextView>(R.id.name_app)

        // Animasi untuk logo dan teks
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val translateUpAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_up)

        // Animasi untuk background
        val scaleUpAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)

        // Jalankan animasi
        logoImageView.startAnimation(fadeInAnimation)
        appNameTextView.startAnimation(translateUpAnimation)
        backgroundImageView.startAnimation(scaleUpAnimation) // Animasi untuk background

        // Navigasi setelah delay
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToHome()
        }, 3000) // Delay 2 detik
    }

    private fun navigateToHome() {
        val intent = Intent(this, LoginActivity::class.java) // Ganti dengan activity tujuan Anda
        startActivity(intent)
        finish() // Tutup SplashActivity
    }
}
