package com.capstone.cookpocket.view.ui.home.cart

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.cookpocket.R
import com.capstone.cookpocket.view.ui.main.MainActivity

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

        // Menambahkan listener untuk tombol kembali
        val backButton = findViewById<ImageView>(R.id.iv_back_cart)
        backButton.setOnClickListener {
            // Kembali ke MainActivity (home)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Opsional, untuk memastikan tidak ada aktivitas lain di stack
            startActivity(intent)
            finish() // Menutup CartActivity setelah kembali ke MainActivity
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
