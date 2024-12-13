package com.capstone.cookpocket.view.ui.search.MenuSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.capstone.cookpocket.Network.Response.Product
import com.capstone.cookpocket.view.ui.home.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MenuSearchViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    // LiveData untuk hasil pencarian tanpa Paging
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    // Flow untuk Paging data berdasarkan query pencarian
    private var _pagingDataFlow: Flow<PagingData<Product>>? = null
    val pagingDataFlow: Flow<PagingData<Product>> get() = _pagingDataFlow!!

    // Fungsi untuk melakukan pencarian produk tanpa Paging
    fun searchProducts(query: String) {
        viewModelScope.launch {
            try {
                // Memanggil repository untuk mencari produk
                val productList = homeRepository.searchProducts(query)
                _products.postValue(productList)  // Update LiveData dengan hasil pencarian
            } catch (e: Exception) {
                // Handle error jika terjadi kesalahan dalam pencarian
                _products.postValue(emptyList())
            }
        }
    }

    // Fungsi untuk pencarian dengan Paging
    fun searchWithPaging(query: String) {
        // Mengambil data Paging dari repository berdasarkan query
        _pagingDataFlow = homeRepository.getPagedSearchResults(query)
    }
}
