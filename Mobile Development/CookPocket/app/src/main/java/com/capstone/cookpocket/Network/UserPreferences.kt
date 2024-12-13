package com.capstone.cookpocket.Network

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
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
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email_key")
        private val USER_ID_KEY = intPreferencesKey("user_id_key")
        private val USER_NO_TELP_KEY = stringPreferencesKey("user_no_telp_key")

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

    // Fungsi untuk mengambil userId
    val idUser: Flow<Int?> = context.dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }

    val noTelp: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_NO_TELP_KEY]
    }

    // Fungsi untuk mengambil token
    val token: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    // Fungsi untuk menyimpan nama pengguna
    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }
    suspend fun saveNoTelp(noTelp: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NO_TELP_KEY] = noTelp
        }
    }

    // Fungsi untuk menyimpan email pengguna
    suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    // Fungsi untuk menyimpan token
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // Fungsi untuk menyimpan userId
    suspend fun saveUserId(userId: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    // Fungsi untuk menghapus token
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    suspend fun clearUserName() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_NAME_KEY)
        }
    }

    // Fungsi untuk mengambil informasi pengguna
    suspend fun getUser(): User {
        val tokenValue = token.firstOrNull()
        val emailValue = userEmail.firstOrNull()
        if (tokenValue.isNullOrEmpty()) {
            throw IllegalStateException("Token tidak ditemukan. Pengguna belum login.")
        }
        return User(token = tokenValue, email = emailValue)
    }

    data class User(
        val token: String,
        val email: String? = null
    )
}
