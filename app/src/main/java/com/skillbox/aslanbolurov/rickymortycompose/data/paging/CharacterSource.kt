package com.skillbox.aslanbolurov.rickymortycompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.characters.Result


class CharacterSource: PagingSource<Int, Result>() {
    private val repository = MainRepository()

    override fun getRefreshKey(state: PagingState<Int, Result>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            repository.getCharacter(page)
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