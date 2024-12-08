//package com.capstone.cookpocket.view.ui.search.detail_search
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.capstone.cookpocket.Database.FavoriteItem
//import com.capstone.cookpocket.Database.FavoriteItemDao
//import com.capstone.cookpocket.Network.Api.ApiConfig
//import com.capstone.cookpocket.Network.Response.StoryDetailResponse
//import com.capstone.cookpocket.Network.Response.ListStoryItem
//import kotlinx.coroutines.launch
//
//class DetailSearchViewModel(private val dao: FavoriteItemDao) : ViewModel() {
//
//    private val _eventDetail = MutableLiveData<ListStoryItem?>()
//    val eventDetail: LiveData<ListStoryItem?> = _eventDetail
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private val _errorMessage = MutableLiveData<String>()
//    val errorMessage: LiveData<String> = _errorMessage
//
//    // Fungsi untuk mengambil detail cerita
//    suspend fun fetchEventDetails(eventId: String) {
//        _isLoading.value = true
//        try {
//            val response = ApiConfig.getApiService().getStoryDetail(eventId)
//            _eventDetail.value = response.story
//        } catch (e: Exception) {
//            _errorMessage.value = "Network error: ${e.message}"
//        } finally {
//            _isLoading.value = false
//        }
//    }
//
//    // Fungsi untuk menambahkan favorit
//    fun insertFavorite(favoriteEvent: FavoriteItem) {
//        viewModelScope.launch {
//            dao.insert(favoriteEvent)
//        }
//    }
//
//
//    // Fungsi untuk menghapus favorit
//    fun deleteFavorite(favoriteEvent: FavoriteItem) {
//        viewModelScope.launch {
//            dao.delete(favoriteEvent)
//        }
//    }
//
//    // Fungsi untuk mengecek apakah cerita sudah ada di favorit
//    fun getFavoriteById(storyId: String): LiveData<FavoriteItem?> {
//        return dao.getFavoriteByStoryId(storyId)
//    }
//}
