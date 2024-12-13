//package com.capstone.cookpocket.view.ui.store.HomeStore
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.capstone.cookpocket.Network.Api.ApiService
//import com.capstone.cookpocket.Network.injections
//import com.capstone.cookpocket.view.ui.home.HomeViewModel
//import kotlinx.coroutines.runBlocking
//
//class StoreViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return runBlocking {
//                val repository = injections.provideRepository(context)
//                HomeViewModel(repository)
//            } as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
