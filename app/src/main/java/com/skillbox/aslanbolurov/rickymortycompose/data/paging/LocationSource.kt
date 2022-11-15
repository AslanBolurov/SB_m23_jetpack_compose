package com.skillbox.aslanbolurov.rickymortycompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.locations.Locations


class LocationSource: PagingSource<Int, Locations>() {
    private val repository = MainRepository()

    override fun getRefreshKey(state: PagingState<Int, Locations>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Locations> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            repository.getLocation(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }
}