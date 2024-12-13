package com.capstone.cookpocket.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cookpocket.Network.Response.Product
import com.capstone.cookpocket.databinding.ActivityAdapterBinding
import com.bumptech.glide.Glide

class AdapterActivity(private val onItemClick: (Product, imageView: ImageView, textView: TextView) -> Unit) :
    ListAdapter<Product, AdapterActivity.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ActivityAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: ActivityAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                // Bind data to UI elements here
                foodName.text = product.name
                Glide.with(itemView.context)
                    .load(product.imageUrl)
                    .into(img)

                root.setOnClickListener {
                    // Handle item click
                    onItemClick(product, img, foodName)
                }
            }
        }
    }

    // DiffUtil for optimizing the RecyclerView updates
    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.idProduct == newItem.idProduct
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
