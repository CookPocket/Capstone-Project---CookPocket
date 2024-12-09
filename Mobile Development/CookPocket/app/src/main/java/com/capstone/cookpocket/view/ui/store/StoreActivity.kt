package com.capstone.cookpocket.view.ui.store

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityStoreBinding
import com.capstone.cookpocket.view.ui.account.AccountFragment

class StoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoreBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_store)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackStore.setOnClickListener {
            var intent = Intent(this, AccountFragment::class.java)
            startActivity(intent)
        }

        binding.fabAddProduct.setOnClickListener {
            var intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }
    }
}