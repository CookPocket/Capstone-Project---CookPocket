package com.capstone.cookpocket.view.uiauth

import com.capstone.cookpocket.Network.Api.ApiService
import com.capstone.cookpocket.Network.Response.LoginResponse
import com.capstone.cookpocket.Network.Response.RegisterResponse

class AuthRepository(private val apiService: ApiService) {

    // Fungsi untuk melakukan register
    suspend fun register(name: String, email: String, password: String, noTelp: String): RegisterResponse {
        val response = apiService.register(name, email, password, noTelp)
        return if (response.error == true) {
            RegisterResponse(error = true, message = response.message, status = response.status, data = response.data)
        } else {
            response
        }
    }

    // Fungsi untuk melakukan login
    suspend fun login(email: String, password: String): LoginResponse {
        val response = apiService.login(email, password)
        return if (response.error == true) {
            LoginResponse(error = true, message = response.message, loginResult = response.loginResult)
        } else {
            response
        }
    }
}