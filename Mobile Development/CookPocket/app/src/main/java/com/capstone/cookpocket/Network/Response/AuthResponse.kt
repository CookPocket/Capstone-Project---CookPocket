package com.capstone.cookpocket.Network.Response

import com.google.gson.annotations.SerializedName

// User Data Class untuk Login dan Register
data class User(
    @field:SerializedName("userId")
    val userId: Int, // Mengubah menjadi non-nullable karena seharusnya tidak null

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("token")
    val token: String // Tidak nullable karena token diharapkan ada pada login
)

// Login Response Data Class
data class LoginResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String, // Menggunakan String non-nullable

    @field:SerializedName("loginResult")
    val loginResult: User // Tidak nullable, karena loginResult seharusnya selalu ada jika login berhasil
)

// Register Response Data Class
data class RegisterResponse(
    @field:SerializedName("error")
    val error: Boolean, // Menggunakan Boolean non-nullable

    @field:SerializedName("status")
    val status: String, // Menggunakan String non-nullable

    @field:SerializedName("message")
    val message: String, // Menggunakan String non-nullable

    @field:SerializedName("data")
    val data: UserRegistrationData?
)

// Data Class untuk Informasi Registrasi
data class UserRegistrationData(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("noTelp")
    val noTelp: String
)

// Logout Response Data Class
data class LogoutResponse(
    @field:SerializedName("error")
    val error: Boolean, // Menggunakan Boolean non-nullable

    @field:SerializedName("message")
    val message: String // Menggunakan String non-nullable
)

// Update Name Response Data Class
data class UpdateNameResponse(
    @field:SerializedName("message")
    val message: String // Menggunakan String non-nullable
)

// Update Email Response Data Class
data class UpdateEmailResponse(
    @field:SerializedName("message")
    val message: String // Menggunakan String non-nullable
)

// Update Phone Response Data Class
data class UpdatePhoneResponse(
    @field:SerializedName("message")
    val message: String // Menggunakan String non-nullable
)

// Update Password Response Data Class
data class UpdatePasswordResponse(
    @field:SerializedName("message")
    val message: String // Menggunakan String non-nullable
)

// Update Address Response Data Class
data class UpdateAddressResponse(
    @field:SerializedName("message")
    val message: String // Menggunakan String non-nullable
)

// Update City Response Data Class
data class UpdateCityResponse(
    @field:SerializedName("message")
    val message: String // Menggunakan String non-nullable
)

// Add Recipe Response Data Class
data class AddRecipeResponse(
    @field:SerializedName("error")
    val error: Boolean, // Menggunakan Boolean non-nullable

    @field:SerializedName("message")
    val message: String, // Menggunakan String non-nullable

    @field:SerializedName("recipeId")
    val recipeId: Int // Menggunakan Int non-nullable karena ID resep harus ada
)

// Data Class untuk Add Recipe Request
data class AddRecipeRequest(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("ingredient")
    val ingredient: String,

    @field:SerializedName("steps")
    val steps: String,

    @field:SerializedName("image_url")
    val imageUrl: String
)
