//package com.capstone.cookpocket.view.ui.search.detail_search
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.capstone.cookpocket.Network.Room.FavoriteItemDao
//import com.capstone.cookpocket.view.ui.list.FavoriteViewModel
//
//class DetailSearchViewModelFactory ( private val dao: FavoriteItemDao) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(DetailSearchViewModel::class.java)) {
//            return DetailSearchViewModel(dao) as T
//        }
//        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
//            return FavoriteViewModel(dao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//    }
//}