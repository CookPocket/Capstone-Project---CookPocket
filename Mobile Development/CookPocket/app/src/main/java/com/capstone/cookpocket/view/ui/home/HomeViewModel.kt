package com.capstone.cookpocket.view.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.cookpocket.Network.Response.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _stories = MutableStateFlow<List<Product>>(emptyList())
    val stories: LiveData<List<Product>> get() = _stories.asLiveData()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: LiveData<Boolean> get() = _isLoading.asLiveData()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: LiveData<String?> get() = _errorMessage.asLiveData()

    // Fungsi untuk memuat cerita dari repository
    fun fetchMakananSehat() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = homeRepository.RepMakananSehat()
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
    fun fetchMakananTradisional() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = homeRepository.RepMakananTradisional()
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
    fun fetchMakananBerat() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = homeRepository.RepMakananBerat()
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
}
