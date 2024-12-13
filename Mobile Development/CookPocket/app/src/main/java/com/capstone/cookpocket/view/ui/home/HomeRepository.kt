package com.capstone.cookpocket.view.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.capstone.cookpocket.Network.Api.ApiService
import com.capstone.cookpocket.Network.Response.Product
import com.capstone.cookpocket.Network.Response.User
import com.capstone.cookpocket.Network.UserPreferences
import com.ichang.mystory.ui.Home.Paging.MakananSehatPagingSource
import com.ichang.mystory.ui.Home.Paging.ProductSearchPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class HomeRepository(
    private val api: ApiService,
    private val userPreferences: UserPreferences
) {
    private val _allStories = MutableLiveData<List<Product>>() // Semua data
    private val _filteredStories = MutableLiveData<List<Product>>() // Data hasil pencarian
    val filteredStories: LiveData<List<Product>> = _filteredStories

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

    // Fungsi untuk mengambil data Makanan Sehat
    suspend fun RepMakananSehat(): Result<List<Product>> {
        return try {
            val token = userPreferences.token.firstOrNull()
            Log.d("StoryRepository", "Menggunakan token: $token")
            if (token.isNullOrEmpty()) {
                Log.e("StoryRepository", "Token tidak ditemukan")
                return Result.failure(Exception("Token tidak ditemukan"))
            }

            val response = api.getMakananSehat()
            Log.d("StoryRepository", "Respons server: ${response.message}")
            if (response.data.isNotEmpty()) {
                Log.d("StoryRepository", "Berhasil mendapatkan cerita: ${response.data.size}")
                Result.success(response.data)
            } else {
                Log.d("StoryRepository", "Daftar cerita kosong")
                Result.failure(Exception("Daftar Kosong"))
            }
        } catch (e: Exception) {
            Log.e("StoryRepository", "getStrories() - Error: ${e.message}")
            Result.failure(Exception("Gagal mengunggah cerita. Penyebab: ${e.message ?: "Kesalahan tidak diketahui"}", e))
        }
    }

    // Fungsi untuk mengambil data Makanan Tradisional
    suspend fun RepMakananTradisional(): Result<List<Product>> {
        return try {
            val token = userPreferences.token.firstOrNull()
            Log.d("StoryRepository", "Menggunakan token: $token")
            if (token.isNullOrEmpty()) {
                Log.e("StoryRepository", "Token tidak ditemukan")
                return Result.failure(Exception("Token tidak ditemukan"))
            }

            val response = api.getMakananTradisional()
            Log.d("StoryRepository", "Respons server: ${response.message}")
            if (response.data.isNotEmpty()) {
                Log.d("StoryRepository", "Berhasil mendapatkan cerita: ${response.data.size}")
                Result.success(response.data)
            } else {
                Log.d("StoryRepository", "Daftar cerita kosong")
                Result.failure(Exception("Daftar Kosong"))
            }
        } catch (e: Exception) {
            Log.e("StoryRepository", "getStrories() - Error: ${e.message}")
            Result.failure(Exception("Gagal mengunggah cerita. Penyebab: ${e.message ?: "Kesalahan tidak diketahui"}", e))
        }
    }

    // Fungsi untuk mengambil data Makanan Berat
    suspend fun RepMakananBerat(): Result<List<Product>> {
        return try {
            val token = userPreferences.token.firstOrNull()
            Log.d("StoryRepository", "Menggunakan token: $token")
            if (token.isNullOrEmpty()) {
                Log.e("StoryRepository", "Token tidak ditemukan")
                return Result.failure(Exception("Token tidak ditemukan"))
            }

            val response = api.getMakananBerat()
            Log.d("StoryRepository", "Respons server: ${response.message}")
            if (response.data.isNotEmpty()) {
                Log.d("StoryRepository", "Berhasil mendapatkan cerita: ${response.data.size}")
                Result.success(response.data)
            } else {
                Log.d("StoryRepository", "Daftar cerita kosong")
                Result.failure(Exception("Daftar Kosong"))
            }
        } catch (e: Exception) {
            Log.e("StoryRepository", "getStrories() - Error: ${e.message}")
            Result.failure(Exception("Gagal mengunggah cerita. Penyebab: ${e.message ?: "Kesalahan tidak diketahui"}", e))
        }
    }

    // Fungsi untuk mengambil data berdasarkan query dengan paging
    fun getPagedSearchResults(query: String): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductSearchPagingSource(api, query) }
        ).flow
    }

    // Fungsi untuk mengambil data dengan paging tanpa filter
    fun getPagedStories(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MakananSehatPagingSource(api) }
        ).flow
    }

    // Fungsi untuk mencari produk dengan query tanpa paging
    suspend fun searchProducts(query: String): List<Product> {
        return try {
            val response = api.searchProducts(query)  // Fungsi yang dipanggil dari ApiService
            response.data // Mengembalikan data produk
        } catch (e: Exception) {
            Log.e("HomeRepository", "searchProducts - Error: ${e.message}")
            emptyList() // Mengembalikan list kosong jika terjadi kesalahan
        }
    }
}
