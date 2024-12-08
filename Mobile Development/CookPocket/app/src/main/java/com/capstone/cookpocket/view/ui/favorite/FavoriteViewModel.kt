//package com.capstone.cookpocket.view.ui.list
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.capstone.cookpocket.Database.FavoriteItem
//import com.capstone.cookpocket.Database.FavoriteItemDao
//
//class FavoriteViewModel(private val favoriteItemDao: FavoriteItemDao) : ViewModel() {
//
//    // Mengambil semua data favorit
//    fun getAllFavorites(): LiveData<List<FavoriteItem>> {
//        return favoriteItemDao.getAllFavorites()
//    }
//
//}