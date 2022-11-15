package com.skillbox.aslanbolurov.rickymortycompose.data


import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.characters.RickAndMortyModel
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.episodes.EpisodeModel
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.locations.LocationModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com"
interface Api {
    @GET("/api/character")
    suspend fun getCharacter(@Query("page") page: Int): RickAndMortyModel

    @GET("/api/location")
    suspend fun getLocationList(@Query("page") page: Int): LocationModel

    @GET("/api/episode/{id}")
    suspend fun getEpisodeList(@Path("id") id: String): List<EpisodeModel>

    @GET("/api/episode/{id}")
    suspend fun getEpisode(@Path("id") id: String): EpisodeModel



    companion object {
        val retrofit: Api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}