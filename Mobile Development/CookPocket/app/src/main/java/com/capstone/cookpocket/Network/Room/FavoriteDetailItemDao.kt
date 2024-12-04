package com.capstone.cookpocket.Network.Room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.cookpocket.view.ui.list.FavoriteViewModel

class FavoriteDetailItemDao ( private val dao: FavoriteItemDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteDetailItemDao::class.java)) {
            return FavoriteDetailItemDao(dao) as T
        }
            if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}