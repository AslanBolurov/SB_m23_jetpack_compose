package com.skillbox.aslanbolurov.rickymortycompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.skillbox.aslanbolurov.rickymortycompose.data.paging.CharacterSource
import com.skillbox.aslanbolurov.rickymortycompose.data.paging.LocationSource
import com.skillbox.aslanbolurov.rickymortycompose.data.paging.MainRepository
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.episodes.EpisodeModel
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.locations.Locations
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.characters.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {
    val characters: Flow<PagingData<Result>> = Pager (
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharacterSource() }
    ).flow.cachedIn(viewModelScope)

    val locations: Flow<PagingData<Locations>> = Pager (
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { LocationSource() }
    ).flow.cachedIn(viewModelScope)

    var result: Result? = null
    var episodes = mutableListOf<EpisodeModel>()

    fun getEpisodeList(episode: List<String>?) {
        viewModelScope.launch {
            val toNumbers = mutableListOf<String>()
                episode?.forEach {
                toNumbers.add(it.replace(Regex("[^0-9]"), ""))
            }

            if (episode?.size == 1) {
                episodes.add(MainRepository().getEpisode(toNumbers.first().toString()))
            } else{
                episodes = MainRepository().getEpisodeList(toNumbers.toString()) as MutableList<EpisodeModel>
            }
        }
    }
}