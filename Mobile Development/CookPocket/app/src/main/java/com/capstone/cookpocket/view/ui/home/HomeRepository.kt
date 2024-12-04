package com.capstone.cookpocket.view.ui.home

import android.util.Log
import com.capstone.cookpocket.Network.Api.ApiService
import com.capstone.cookpocket.Network.Response.FileUploadResponse
import com.capstone.cookpocket.Network.Response.ListStoryItem
import com.capstone.cookpocket.Network.UserPreferences
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class HomeRepository(
    private val api: ApiService,
    private val userPreferences: UserPreferences
) {

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
}
