package com.capstone.cookpocket.view.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.Network.Room.FavoriteItemDatabase
import com.capstone.cookpocket.R
import com.capstone.cookpocket.view.ui.adapter.AdapterActivity
import com.capstone.cookpocket.view.ui.main.MainActivity
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: AdapterActivity
    private lateinit var progressBar: ProgressBar
    private lateinit var noFavoritesText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_favorite)
        progressBar = view.findViewById(R.id.progresbar_favorite)
        noFavoritesText = view.findViewById(R.id.no_favorites_text)

        adapter = AdapterActivity(emptyList()) { eventId ->
            val intent = Intent(requireContext(), DetailSearchActivity::class.java).apply {
                putExtra("EVENT_ID", eventId)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dao = FavoriteItemDatabase.getDatabase(requireContext()).favoriteItemDao()
        val factory = FavoriteViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)

        observeFavorites()

        return view
    }

    private fun observeFavorites() {
        viewModel.getFavoriteEvents().observe(viewLifecycleOwner) { favorites ->
            progressBar.visibility = View.VISIBLE

            if (favorites.isNullOrEmpty()) {
                progressBar.visibility = View.GONE
                noFavoritesText.visibility =
                    View.VISIBLE
                adapter.submitList(emptyList())
            } else {
                progressBar.visibility = View.GONE
                noFavoritesText.visibility = View.GONE

                val items = favorites.map { favorite ->
                    ListStoryItem(
//                        id = favorite.id.toIntOrNull() ?: 0,
                        name = favorite.name ,
                        photoUrl = favorite.mediaCover
                    )
                }
                adapter.submitList(ArrayList(items))
            }
        }
    }
}
