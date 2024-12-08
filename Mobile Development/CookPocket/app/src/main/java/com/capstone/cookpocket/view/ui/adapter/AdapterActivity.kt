package com.capstone.cookpocket.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide  // Corrected import
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.databinding.ActivityAdapterBinding

class AdapterActivity(
    private val onItemClick: (ListStoryItem, ImageView, TextView) -> Unit
) : RecyclerView.Adapter<AdapterActivity.StoryViewHolder>() {

    private var stories = mutableListOf<ListStoryItem>()

    fun submitList(newEvents: List<ListStoryItem>) {
        stories = newEvents.toMutableList()
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

        val imageView: ImageView = binding.img
        val textView: TextView = binding.foodName

        fun bind(story: ListStoryItem) {
            binding.foodName.text = story.name
            binding.foodDescription.text = story.description

            Glide.with(binding.img.context)
                .load(story.photoUrl)
                .into(binding.img)

            binding.root.setOnClickListener {
                onItemClick(story, imageView, textView)
            }
        }
    }
}
