package com.capstone.cookpocket.view.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.cookpocket.Network.Room.FavoriteItem
import com.capstone.cookpocket.Network.Room.FavoriteItemDao
import kotlinx.coroutines.launch

class FavoriteViewModel(private val dao: FavoriteItemDao) : ViewModel() {
    fun getFavoriteEvents(): LiveData<List<FavoriteItem>> {
        return dao.getAllFavorites()
    }
    fun removeFavorite(event: FavoriteItem) {
        viewModelScope.launch {
            dao.deleteFavorite(event)
        }
    }

}