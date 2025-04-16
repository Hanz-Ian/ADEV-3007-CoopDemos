package com.example.coopdemo1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a character from the Rick and Morty API.
 * @param id the ID of the character.
 * @param name the name of the character.
 * @param image the URL of the character's image.
 */
@Entity(tableName = "characters")
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String
)
