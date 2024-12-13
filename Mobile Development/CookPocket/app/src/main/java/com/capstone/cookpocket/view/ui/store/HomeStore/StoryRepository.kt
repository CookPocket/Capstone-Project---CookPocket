//package com.capstone.cookpocket.view.ui.store.HomeStore
//
//import android.util.Log
//import com.capstone.cookpocket.Network.Api.ApiService
//import com.capstone.cookpocket.Network.Response.ListStoryItem
//import kotlinx.coroutines.flow.firstOrNull
//
//class StoreRepository(
//    private val api: ApiService
//) {
//    companion object {
//        @Volatile
//        private var INSTANCE: StoreRepository? = null
//
//        fun getInstance(api: ApiService): StoreRepository {
//            return INSTANCE ?: synchronized(this) {
//                val instance = StoreRepository(api)
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//
//    suspend fun getStories(): Result<List<ListStoryItem>> {
//        return try {
//            val response = api.getAllStories()
//            if (response.listStory.isNotEmpty()) {
//                Result.success(response.listStory)
//            } else {
//                Result.failure(Exception("Daftar cerita kosong"))
//            }
//        } catch (e: Exception) {
//            Log.e("StoreRepository", "getStories() - Error: ${e.message}")
//            Result.failure(Exception("Gagal mengambil cerita. Penyebab: ${e.message ?: "Kesalahan tidak diketahui"}", e))
//        }
//    }
//}
