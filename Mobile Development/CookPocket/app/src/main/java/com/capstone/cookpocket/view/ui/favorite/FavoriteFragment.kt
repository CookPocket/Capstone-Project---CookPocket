package com.capstone.cookpocket.view.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cookpocket.R
import com.capstone.cookpocket.view.ui.adapter.AdapterActivity
import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity

class FavoriteFragment : Fragment() {

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

        return view
    }


}