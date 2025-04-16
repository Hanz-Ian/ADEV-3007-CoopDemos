package com.example.coopdemo1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coopdemo1.model.Character
import com.example.coopdemo1.network.AppDatabase
import com.example.coopdemo1.network.RetrofitAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing character data.
 * Fetches character data from the Rick and Morty API and caches it locally using Room.
 */
class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    // Mutable state flow to hold the character data
    private val _character = MutableStateFlow<Character?>(null)

    // Public state flow to expose the character data
    val character: StateFlow<Character?> = _character

    // DAO instance for accessing the Room database
    private val characterDao = AppDatabase.getDatabase(application).characterDao()

    // Initialize the ViewModel by fetching the first character
    init {
        fetchCharacter(1)
    }

    /**
     * Fetches character data by ID.
     * First attempts to retrieve the data from the local Room database.
     * If not found, fetches the data from the API and caches it locally.
     * @param id the ID of the character to fetch
     */
    fun fetchCharacter(id: Int) {
        viewModelScope.launch {
            // Check if the character is already in the database
            val cachedCharacter = characterDao.getCharacterById(id)
            if (cachedCharacter != null) {
                // If the character exists, update the state flow
                _character.value = cachedCharacter
            } else {
                // If the character doesn't exist, fetch it from the API
                val response = RetrofitAPI.api.getCharacterById(id)
                // Update the state flow with the fetched character
                _character.value = response
                // Add the character to the database
                characterDao.insertCharacter(response)
            }
        }
    }
}