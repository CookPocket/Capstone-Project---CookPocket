package com.capstone.cookpocket.view.ui.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.databinding.ActivityAdapterBinding

class CookPocketHomeAdapter(
    private val onItemClick: (ListStoryItem, ImageView, TextView) -> Unit
) : RecyclerView.Adapter<CookPocketHomeAdapter.StoryViewHolder>() {

    private val stories = mutableListOf<ListStoryItem>()

    fun setStories(newStories: List<ListStoryItem>) {
        Log.d("CookPocketHomeAdapter", "Data diterima adapter: ${newStories.size} cerita")
        stories.clear()
        stories.addAll(newStories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ActivityAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size

    inner class StoryViewHolder(private val binding: ActivityAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(story: ListStoryItem) {
            binding.foodName.text = story.name
            binding.foodDescription.text = story.description

            // Memuat gambar menggunakan Glide
            Glide.with(binding.img.context)
                .load(story.photoUrl)
                .into(binding.img)

            // Set click listener pada item
            binding.root.setOnClickListener {
                onItemClick(story, binding.img, binding.foodName)
            }
        }
    }
}
