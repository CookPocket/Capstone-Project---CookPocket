package com.capstone.cookpocket.view.ui.search.MenuSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.view.ui.home.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MenuSearchViewModel(private val homeRepository: HomeRepository) : ViewModel() {

        // Properti untuk data paginasi
        var pagedStories: Flow<PagingData<ListStoryItem>> = homeRepository.getPagedStories()
            .cachedIn(viewModelScope)

        // Fungsi untuk memperbarui data berdasarkan query pencarian
        fun searchStories(query: String) {
            pagedStories = homeRepository.getPagedStories(query).cachedIn(viewModelScope)
        }
    // Fungsi untuk mendapatkan data paginasi berdasarkan query
    fun getPagedStories(query: String): Flow<PagingData<ListStoryItem>> {
        return homeRepository.getPagedStories(query).cachedIn(viewModelScope)
    }

}
