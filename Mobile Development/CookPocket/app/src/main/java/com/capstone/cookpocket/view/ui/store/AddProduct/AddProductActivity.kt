package com.capstone.cookpocket.view.ui.store.AddProduct

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.cookpocket.R
import com.capstone.cookpocket.databinding.ActivityAddProductBinding
import com.capstone.cookpocket.view.ui.store.HomeStore.StoreActivity


class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackAddProduct.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }
    }
}