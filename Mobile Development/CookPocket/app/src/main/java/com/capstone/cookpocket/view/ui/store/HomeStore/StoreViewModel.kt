//package com.capstone.cookpocket.view.ui.store.HomeStore
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.asLiveData
//import androidx.lifecycle.viewModelScope
//import com.capstone.cookpocket.Network.Response.ListStoryItem
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.launch
//
//class StoreViewModel(private val storeRepository: StoreRepository) : ViewModel() {
//
//    private val _stories = MutableStateFlow<List<ListStoryItem>>(emptyList())
//    val stories: LiveData<List<ListStoryItem>> = _stories.asLiveData()
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: LiveData<Boolean> = _isLoading.asLiveData()
//
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage: LiveData<String?> = _errorMessage.asLiveData()
//
//    fun fetchStories() {
//        viewModelScope.launch {
//            _isLoading.value = true
//            _errorMessage.value = null
//
//            val result = storeRepository.getStories()
//
//            if (result.isSuccess) {
//                _stories.value = result.getOrDefault(emptyList())
//                Log.d("StoreViewModel", "Berhasil mengambil cerita: ${_stories.value.size}")
//            } else {
//                val errorMessage = result.exceptionOrNull()?.message ?: "Terjadi kesalahan"
//                _errorMessage.value = errorMessage
//                Log.e("StoreViewModel", "Gagal mengambil cerita: $errorMessage")
//            }
//
//            _isLoading.value = false
//        }
//    }
//}
