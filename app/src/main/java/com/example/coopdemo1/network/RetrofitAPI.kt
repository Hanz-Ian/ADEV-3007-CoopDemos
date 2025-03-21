package com.example.coopdemo1.network

import com.google.firebase.appdistribution.gradle.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to provide Retrofit instance and API service.
 */
object RetrofitAPI {
    // Base URL for the Rick and Morty API
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    // Lazy initialization of the Retrofit API service
    val api: RickAndMortyService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Set the base URL
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter
            .build() // Build the Retrofit instance
            .create(RickAndMortyService::class.java) // Create the API service
    }
}