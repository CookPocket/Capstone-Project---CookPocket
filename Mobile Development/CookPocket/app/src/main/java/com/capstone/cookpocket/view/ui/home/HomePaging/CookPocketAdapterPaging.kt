package com.capstone.cookpocket.view.ui.home.HomePaging

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.databinding.ActivityAdapterBinding
import com.capstone.cookpocket.view.ui.adapter.AdapterActivity

class CookPocketPagingAdapter(
    private val onItemClick: (ListStoryItem) -> Unit // Listener onItemClick untuk menangani klik item
) : PagingDataAdapter<ListStoryItem, CookPocketPagingAdapter.CookPocketViewHolder>(DIFF_CALLBACK) {

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
        private val onItemClick: (ListStoryItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(story: ListStoryItem) {
            binding.foodName.text = story.name
            binding.foodDescription.text = story.description

            // Memuat gambar menggunakan Glide
            Glide.with(binding.img.context)
                .load(story.photoUrl)
                .into(binding.img)

            // Menambahkan klik listener
            binding.root.setOnClickListener {
                onItemClick(story)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    // Fungsi untuk memicu pembaruan ulang RecyclerView
    fun refreshData() {
        notifyDataSetChanged()
    }
}
