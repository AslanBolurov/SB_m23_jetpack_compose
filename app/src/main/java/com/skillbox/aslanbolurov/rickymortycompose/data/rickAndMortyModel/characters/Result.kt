package com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.characters

data class Result(
    val id: Int,
    val location: Location,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val origin: Origin
)