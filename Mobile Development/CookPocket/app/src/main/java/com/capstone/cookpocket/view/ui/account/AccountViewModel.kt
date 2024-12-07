package com.capstone.cookpocket.view.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.cookpocket.Network.UserPreferences
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class AccountViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    fun clearUserData() = runBlocking {
        userPreferences.clearToken()
        userPreferences.clearUserName()
    }
}
