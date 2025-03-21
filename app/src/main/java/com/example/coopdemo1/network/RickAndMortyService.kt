package com.example.coopdemo1.network

import com.example.coopdemo1.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service interface for the Rick and Morty API.
 * Defines the endpoints to interact with the API.
 */
interface RickAndMortyService {
    /**
     * Fetches a character by ID.
     * @param id the ID of the character to fetch.
     * @return the character data.
     */
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character
}