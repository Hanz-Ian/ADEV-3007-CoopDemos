package com.example.coopdemo1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coopdemo1.model.Character
import com.example.coopdemo1.network.RetrofitAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing character data.
 * Fetches character data from the Rick and Morty API.
 */
class CharacterViewModel : ViewModel() {
    // Mutable state flow to hold the character data
    private val _character = MutableStateFlow<Character?>(null)
    // Public state flow to expose the character data
    val character: StateFlow<Character?> = _character

    // Initialize the ViewModel by fetching the first character
    init {
        fetchCharacter(1)
    }

    /**
     * Fetches character data by ID from the API.
     * @param id the ID of the character to fetch
     */
    fun fetchCharacter(id: Int) {
        viewModelScope.launch {
            // Make the network request to fetch the character data
            val response = RetrofitAPI.api.getCharacterById(id)
            // Update the state flow with the fetched character data
            _character.value = response
        }
    }
}