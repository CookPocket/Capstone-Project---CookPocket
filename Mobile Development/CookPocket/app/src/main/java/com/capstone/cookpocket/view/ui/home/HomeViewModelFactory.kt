package com.capstone.cookpocket.view.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.cookpocket.Network.injections
import kotlinx.coroutines.runBlocking

class HomeViewModelFactory (private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return runBlocking {
                val repository = injections.provideRepository(context)
                HomeViewModel(repository)
            } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
