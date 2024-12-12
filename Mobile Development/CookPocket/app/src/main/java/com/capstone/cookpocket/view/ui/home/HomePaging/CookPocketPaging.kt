package com.capstone.cookpocket.view.ui.home.HomePaging

import android.graphics.pdf.LoadParams
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.cookpocket.Network.Api.ApiService
import com.capstone.cookpocket.Network.Response.ListStoryItem

class CookPocketPaging(
    private val apiService: ApiService,
    private val query: String? = null
) : PagingSource<Int, ListStoryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListStoryItem> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getAllStories(page = page, size = params.loadSize)

            // Filter stories berdasarkan query
            val filteredStories = response.listStory.filter { story ->
                // Jika query null atau kosong, tampilkan semua cerita
                query.isNullOrEmpty() ||
                        // Jika story.name atau story.description tidak null, lakukan pencarian dengan contains
                        story.name?.contains(query.orEmpty(), ignoreCase = true) == true ||
                        story.description?.contains(query.orEmpty(), ignoreCase = true) == true
            }

            LoadResult.Page(
                data = filteredStories,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (filteredStories.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ListStoryItem>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
