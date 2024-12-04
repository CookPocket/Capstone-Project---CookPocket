package com.capstone.cookpocket.Network

import android.content.Context
import android.util.Log
import com.capstone.cookpocket.Network.Api.ApiConfig
import com.capstone.cookpocket.view.ui.home.HomeRepository

object injections {
    suspend fun provideRepository(context: Context): HomeRepository {
        // Mengambil instance UserPreferences
        val pref = UserPreferences.getInstance(context)

        // Mendapatkan token pengguna secara asinkron
        val user = pref.getUser() // Fungsi ini akan berjalan secara suspended
        Log.d("Injection", "Token ditemukan: ${user.token}")

        // Menggunakan token untuk konfigurasi ApiService
        val apiService = ApiConfig.getApiService(user.token)
        Log.d("Injection", "ApiService berhasil dibuat dengan token")

        // Mengembalikan instance StoryRepository
        return HomeRepository.getInstance(apiService, pref)
    }
}