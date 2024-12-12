package com.capstone.cookpocket.view.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.cookpocket.Network.Response.FileUploadResponse
import com.capstone.cookpocket.Network.Response.ListStoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class HomeViewModel(private val storyRepository: HomeRepository) : ViewModel() {

    private val _stories = MutableStateFlow<List<ListStoryItem>>(emptyList())
    val stories: LiveData<List<ListStoryItem>> get() = _stories.asLiveData()

    private val _uploadResult = MutableStateFlow<FileUploadResponse?>(null)
    val uploadResult: LiveData<FileUploadResponse?> get() = _uploadResult.asLiveData()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: LiveData<Boolean> get() = _isLoading.asLiveData()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: LiveData<String?> get() = _errorMessage.asLiveData()

    // Fungsi untuk memuat cerita dari repository
    fun fetchStories() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = storyRepository.getStories()
                if (result.isSuccess) {
                    _stories.value = result.getOrDefault(emptyList())
                    Log.d("HomeViewModel", "Berhasil mengambil cerita: ${_stories.value.size}")
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Terjadi kesalahan"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan saat mengambil cerita"
                Log.e("HomeViewModel", "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Fungsi untuk mengunggah cerita
    fun uploadStory(photo: MultipartBody.Part, description: RequestBody) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = storyRepository.uploadStory(photo, description)
                if (result.isSuccess) {
                    _uploadResult.value = result.getOrNull()
                    Log.d("HomeViewModel", "Upload berhasil")
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Gagal mengunggah cerita"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan saat mengunggah cerita"
                Log.e("HomeViewModel", "Upload Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
