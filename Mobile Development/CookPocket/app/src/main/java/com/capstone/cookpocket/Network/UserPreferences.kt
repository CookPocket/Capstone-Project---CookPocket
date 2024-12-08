package com.capstone.cookpocket.Network

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token_key")
        private val USER_NAME_KEY = stringPreferencesKey("user_name_key")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email_key") // Kunci untuk email pengguna
        private val USER_IMAGE_URL_KEY = stringPreferencesKey("user_image_url_key") // Kunci untuk gambar pengguna

        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(context: Context): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(context)
                INSTANCE = instance
                instance
            }
        }
    }

    // Fungsi untuk mengambil nama pengguna
    val userName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY]
    }

    // Fungsi untuk mengambil email pengguna
    val userEmail: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL_KEY]
    }

    // Fungsi untuk mengambil gambar pengguna
    val userImageUrl: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_IMAGE_URL_KEY]
    }

    // Fungsi untuk mengambil token
    val token: Flow<String?> = context.dataStore.data.map { preferences ->
        val savedToken = preferences[TOKEN_KEY]
        Log.d("UserPreferences", "Mengambil token: $savedToken")
        savedToken
    }

    // Fungsi untuk menyimpan nama pengguna
    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    // Fungsi untuk menyimpan email pengguna
    suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    // Fungsi untuk menyimpan gambar pengguna
    suspend fun saveUserImageUrl(imageUrl: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_IMAGE_URL_KEY] = imageUrl
        }
    }

    // Fungsi untuk menyimpan token
    suspend fun saveToken(token: String) {
        Log.d("UserPreferences", "Menyimpan token: $token")
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
        Log.d("UserPreferences", "Token berhasil disimpan.")
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
        Log.d("UserPreferences", "Token berhasil dihapus.")
    }

    suspend fun clearUserName() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_NAME_KEY)
        }
    }

    suspend fun clearUserEmail() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_EMAIL_KEY)
        }
    }

    suspend fun clearUserImageUrl() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_IMAGE_URL_KEY)
        }
    }

    suspend fun getUser(): User {
        val tokenValue = token.firstOrNull()
        val emailValue = userEmail.firstOrNull() // Mendapatkan email
        if (tokenValue.isNullOrEmpty()) {
            throw IllegalStateException("Token tidak ditemukan. Pengguna belum login.")
        }
        Log.d("UserPreferences", "Token ditemukan: $tokenValue")
        return User(token = tokenValue, email = emailValue)
    }

    data class User(
        val token: String,
        val email: String? = null // Menambahkan email ke dalam objek User
    )
}
