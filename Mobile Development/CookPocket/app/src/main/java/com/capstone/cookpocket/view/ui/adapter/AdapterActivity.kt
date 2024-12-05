package com.capstone.cookpocket.view.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.R

class AdapterActivity (
    private var events: List<ListStoryItem>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<AdapterActivity.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleEvent: TextView = itemView.findViewById(R.id.tv_judul)
        private val imgEvent: ImageView = itemView.findViewById(R.id.img)

        fun bind(event: ListStoryItem) {
            titleEvent.text = event.name
            Glide.with(itemView.context)
                .load(event.photoUrl)
                .into(imgEvent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)

        holder.itemView.setOnClickListener {
            event.id?.let { eventId ->
                onItemClick(eventId.toString()) // Mengirimkan ID event sebagai String
            } ?: run {
                onItemClick("") // Handle jika ID null
            }
        }
    }

    override fun getItemCount(): Int = events.size

    fun updateEvents(newEvents: List<ListStoryItem>) {
        events = newEvents
        notifyDataSetChanged()
    }
    fun submitList(newEvents: List<ListStoryItem>) {
        events = newEvents
        notifyDataSetChanged()
    }

}
