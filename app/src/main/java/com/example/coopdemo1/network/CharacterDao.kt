package com.example.coopdemo1.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coopdemo1.model.Character

/**
 * Data Access Object (DAO) for the Character entity.
 * Provides methods to interact with the Room database.
 */
@Dao
interface CharacterDao {
    /**
     * Inserts a character into the database.
     * Replaces the existing entry if there is a conflict.
     * @param character the character to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    /**
     * Fetches a character by ID from the database.
     * @param id the ID of the character to fetch.
     * @return the character if found, or null otherwise.
     */
    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): Character?
}
