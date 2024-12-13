package com.capstone.cookpocket.view.ui.home.HomePaging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cookpocket.Network.Response.Product
import com.capstone.cookpocket.databinding.ActivityAdapterBinding

class CookPocketPagingAdapter(
    private val onItemClick: (Product) -> Unit // Listener onItemClick untuk menangani klik item
) : PagingDataAdapter<Product, CookPocketPagingAdapter.CookPocketViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookPocketViewHolder {
        val binding = ActivityAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CookPocketViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CookPocketViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
        }
    }

    class CookPocketViewHolder(
        private val binding: ActivityAdapterBinding,
        private val onItemClick: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.foodName.text = product.name
            binding.foodDescription.text = product.description

            // Memuat gambar menggunakan Glide
            Glide.with(binding.img.context)
                .load(product.imageUrl)
                .into(binding.img)

            // Menambahkan klik listener
            binding.root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.idProduct == newItem.idProduct
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }

    // Fungsi untuk memicu pembaruan ulang RecyclerView
    fun refreshData() {
        notifyDataSetChanged()
    }
}
