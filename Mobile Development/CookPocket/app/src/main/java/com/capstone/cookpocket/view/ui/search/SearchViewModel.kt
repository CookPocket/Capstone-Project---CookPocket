package com.capstone.cookpocket.view.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.cookpocket.Network.Response.FileUploadResponse
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.view.ui.home.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel (private val storyRepository: HomeRepository) : ViewModel() {

    private val _stories = MutableStateFlow<List<ListStoryItem>>(emptyList())
    val stories = _stories.asLiveData() // Konversi ke LiveData

    private val _uploadResult = MutableStateFlow<FileUploadResponse?>(null)
    val uploadResult = _uploadResult.asLiveData() // Konversi ke LiveData

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asLiveData() // Konversi ke LiveData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asLiveData()

    fun fetchStories() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = storyRepository.getStories()

            if (result.isSuccess) {
                _stories.value = result.getOrDefault(emptyList())
                Log.d("StoryViewModel", "Berhasil mengambil cerita: ${_stories.value.size}")
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "Terjadi kesalahan"
                _errorMessage.value = errorMessage
                Log.e("StoryViewModel", "Gagal mengambil cerita: $errorMessage")
            }

            _isLoading.value = false
        }
    }
}