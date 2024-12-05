//package com.capstone.cookpocket.view.ui.search.detail_search
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.capstone.cookpocket.Network.Api.ApiConfig
//import com.capstone.cookpocket.Network.Response.CookPocket
//import com.capstone.cookpocket.Network.Response.DetailCookPocketResponse
//import com.capstone.cookpocket.Network.Room.FavoriteItem
//import com.capstone.cookpocket.Network.Room.FavoriteItemDao
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class DetailSearchViewModel(private val dao: FavoriteItemDao) : ViewModel() {
//    private val _eventDetail = MutableLiveData<CookPocket?>()
//    val eventDetail: LiveData<CookPocket?> = _eventDetail
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private val _errorMessage = MutableLiveData<String>()
//    val errorMessage: LiveData<String> = _errorMessage
//
//    fun fetchEventDetails(eventId: String) {
//        _isLoading.value = true
//        ApiConfig.CookPocketApiService.getDetails(eventId).enqueue(object :
//            Callback<DetailCookPocketResponse> {
//            override fun onResponse(call: Call<DetailCookPocketResponse>, response: Response<DetailCookPocketResponse>) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val event = response.body()?.cookPocket
//                    if (event != null) {
//                        _eventDetail.value = event
//                    } else {
//                        _errorMessage.value = "Event details are empty"
//                    }
//                } else {
//                    _errorMessage.value = "Failed to fetch event details: ${response.code()}"
//                }
//            }
//
//            override fun onFailure(call: Call<DetailCookPocketResponse>, t: Throwable) {
//                _isLoading.value = false
//                _errorMessage.value = "Network error: ${t.message}"
//            }
//        })
//    }
//
//    fun getFavoriteEventById(id: String): LiveData<FavoriteItem?> = dao.getFavoriteById(id)
//
//    fun insertFavorite(favoriteEvent: FavoriteItem) {
//        viewModelScope.launch {
//            dao.insertFavorite(favoriteEvent)
//        }
//    }
//
//    fun deleteFavorite(favoriteEvent: FavoriteItem) {
//        viewModelScope.launch {
//            dao.deleteFavorite(favoriteEvent)
//        }
//    }
//
//}