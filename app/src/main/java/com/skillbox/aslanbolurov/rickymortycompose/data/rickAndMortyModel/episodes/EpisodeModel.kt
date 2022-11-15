package com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.episodes

data class EpisodeModel(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)