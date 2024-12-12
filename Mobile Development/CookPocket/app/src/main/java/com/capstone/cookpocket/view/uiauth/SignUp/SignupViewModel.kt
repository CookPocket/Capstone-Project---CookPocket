package com.capstone.cookpocket.view.uiauth.SignUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.cookpocket.Network.Response.RegisterResponse
import com.capstone.cookpocket.view.uiauth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignupViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val _registerState = MutableStateFlow<RegisterResponse?>(null)
    val registerState: StateFlow<RegisterResponse?> = _registerState

    fun register(name: String, email: String, password: String, noTelp: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.register(name, email, password, noTelp)
                _registerState.value = response
            } catch (e: Exception) {
                _registerState.value = RegisterResponse(error = true, message = e.message?: "", status = "", data = null)
            }
        }
    }
}