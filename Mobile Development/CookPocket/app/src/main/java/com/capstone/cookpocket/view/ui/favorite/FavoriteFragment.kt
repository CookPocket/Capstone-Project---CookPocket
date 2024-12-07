//package com.capstone.cookpocket.view.ui.list
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ProgressBar
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.capstone.cookpocket.Database.FavoriteItemDatabase
//import com.capstone.cookpocket.R
//import com.capstone.cookpocket.view.ui.adapter.AdapterActivity
//import com.capstone.cookpocket.view.ui.search.detail_search.DetailSearchActivity
//import com.capstone.cookpocket.Network.Response.ListStoryItem
//
//class FavoriteFragment : Fragment() {
//
//    private lateinit var viewModel: FavoriteViewModel
//    private lateinit var adapter: AdapterActivity
//    private lateinit var progressBar: ProgressBar
//    private lateinit var noFavoritesText: TextView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
//
//        val recyclerView: RecyclerView = view.findViewById(R.id.rv_favorite)
//        progressBar = view.findViewById(R.id.progresbar_favorite)
//        noFavoritesText = view.findViewById(R.id.no_favorites_text)
//
//        // Inisialisasi adapter dengan daftar kosong awal dan penanganan klik
//        adapter = AdapterActivity { story, imageView, textView ->
//            val intent = Intent(requireContext(), DetailSearchActivity::class.java).apply {
//                // Mengirimkan data lengkap untuk DetailSearchActivity
//                putExtra("EVENT_ID", story.id)  // Pastikan id valid
//                putExtra("STORY_NAME", story.name)  // Nama cerita
//                putExtra("STORY_DESCRIPTION", story.description)  // Deskripsi cerita
//                putExtra("STORY_PHOTO", story.photoUrl)  // URL foto cerita jika diperlukan
//            }
//            startActivity(intent)
//        }
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        // Inisialisasi ViewModel
//        val dao = FavoriteItemDatabase.getDatabase(requireContext()).favoriteItemDao()
//        viewModel = ViewModelProvider(this, FavoriteViewModelFactory(dao)).get(FavoriteViewModel::class.java)
//
//        // Observasi data favorit
//        observeFavorites()
//
//        return view
//    }
//
//    private fun observeFavorites() {
//        viewModel.getAllFavorites().observe(viewLifecycleOwner) { favorites ->
//            progressBar.visibility = View.GONE
//            if (favorites.isEmpty()) {
//                noFavoritesText.visibility = View.VISIBLE
//                adapter.submitList(emptyList())
//            } else {
//                noFavoritesText.visibility = View.GONE
//                // Menyiapkan data dari daftar favorit untuk adapter
//                val items = favorites.map { favorite ->
//                    ListStoryItem(
//                        id = favorite.storyId,
//                        name = favorite.name,
//                        description = favorite.description,
//                        photoUrl = favorite.photoUrl
//                    )
//                }
//                adapter.submitList(items)
//            }
//        }
//    }
//}