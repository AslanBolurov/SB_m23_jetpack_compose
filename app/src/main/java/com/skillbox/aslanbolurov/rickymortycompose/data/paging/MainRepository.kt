package com.skillbox.aslanbolurov.rickymortycompose.data.paging


import com.skillbox.aslanbolurov.rickymortycompose.data.Api.Companion.retrofit
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.episodes.EpisodeModel
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.locations.Locations
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.characters.Result

class MainRepository {
    suspend fun getCharacter(page: Int): List<Result> {
        return retrofit.getCharacter(page).results
    }

    suspend fun getLocation(page: Int): List<Locations> {
        return retrofit.getLocationList(page).results
    }

    suspend fun getEpisodeList(id: String): List<EpisodeModel> {
        return retrofit.getEpisodeList(id)
    }

    suspend fun getEpisode(id: String): EpisodeModel {
        return retrofit.getEpisode(id)
    }
}