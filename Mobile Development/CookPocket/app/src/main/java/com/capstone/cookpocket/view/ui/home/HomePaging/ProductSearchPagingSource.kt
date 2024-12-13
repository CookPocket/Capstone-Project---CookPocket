package com.ichang.mystory.ui.Home.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.cookpocket.Network.Api.ApiService
import com.capstone.cookpocket.Network.Response.Product
import retrofit2.HttpException
import java.io.IOException

class ProductSearchPagingSource(
    private val api: ApiService,
    private val query: String
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1

        return try {
            // Call the API to get products based on the query
            val response = api.searchProducts(query, page)

            // If response is successful
            val products = response.data

            LoadResult.Page(
                data = products,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (products.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        // Returning null will refresh from the first page
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey }
    }
}
