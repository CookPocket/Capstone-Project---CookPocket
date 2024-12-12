package com.capstone.cookpocket.view.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.capstone.cookpocket.Network.Api.ApiService
import com.capstone.cookpocket.Network.Response.FileUploadResponse
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.Network.UserPreferences
import com.capstone.cookpocket.view.ui.home.HomePaging.CookPocketPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class HomeRepository(
    private val api: ApiService,
    private val userPreferences: UserPreferences
) {
    private val _allStories = MutableLiveData<List<ListStoryItem>>() // Semua data
    private val _filteredStories = MutableLiveData<List<ListStoryItem>>() // Data hasil pencarian
    val filteredStories: LiveData<List<ListStoryItem>> = _filteredStories

    companion object {
        @Volatile
        private var INSTANCE: HomeRepository? = null

        fun getInstance(api: ApiService, userPreferences: UserPreferences): HomeRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = HomeRepository(api, userPreferences)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun getStories(): Result<List<ListStoryItem>> {
        return try {
            val token = userPreferences.token.firstOrNull()
            Log.d("StoryRepository", "Menggunakan token: $token")
            if (token.isNullOrEmpty()) {
                Log.e("StoryRepository", "Token tidak ditemukan")
                return Result.failure(Exception("Token tidak ditemukan"))
            }

            val response = api.getAllStories()
            Log.d("StoryRepository", "Respons server: ${response.message}")
            if (response.listStory.isNotEmpty()) {
                Log.d("StoryRepository", "Berhasil mendapatkan cerita: ${response.listStory.size}")
                Result.success(response.listStory)
            } else {
                Log.d("StoryRepository", "Daftar cerita kosong")
                Result.failure(Exception("Daftar Kosong"))
            }
        } catch (e: Exception) {
            Log.e("StoryRepository", "getStrories() - Error: ${e.message}")
            Result.failure(Exception("Gagal mengunggah cerita. Penyebab: ${e.message ?: "Kesalahan tidak diketahui"}", e))
        }
    }

    suspend fun uploadStory(photo: MultipartBody.Part, description: RequestBody): Result<FileUploadResponse> {
        return try {
            val token = userPreferences.token.firstOrNull()
                ?: return Result.failure(Exception("Token tidak ditemukan"))

            Log.d("Token", "Token: $token") // Debug token

            val response = api.uploadStory(photo, description)
            if (response.error) {
                Result.failure(Exception(response.message))
            } else {
                Result.success(response)
            }
        } catch (e: Exception) {
            Log.e("UploadStoryError", "Error: ${e.localizedMessage}") // Debug error
            Result.failure(Exception("Gagal mengunggah: ${e.localizedMessage}", e))
        }
    }

    suspend fun clearToken() {
        userPreferences.clearToken()
    }

    fun searchStories(query: String) {
        // Filter berdasarkan nama atau deskripsi
        val stories = _allStories.value ?: emptyList()
        _filteredStories.value = stories.filter { story ->
            (story.name?.contains(query, ignoreCase = true) ?: false) ||
                    (story.description?.contains(query, ignoreCase = true) ?: false)
        }
    }
    fun getPagedStories(query: String? = null): Flow<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false // Tidak menampilkan placeholder
            ),
            pagingSourceFactory = { CookPocketPaging(api, query) }
        ).flow
    }

}
